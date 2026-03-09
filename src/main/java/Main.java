import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    private static final String API_KEY = "03a7f2832bf5be74f5c85eac";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        String menu = """
                \n*******************************************************
                Seja bem-vindo/a ao Conversor de Moedas!
                
                Selecione uma opção de conversão:
                1) Dólar (USD) => Real brasileiro (BRL)
                2) Real brasileiro (BRL) => Dólar (USD)
                3) Euro (EUR) => Real brasileiro (BRL)
                4) Real brasileiro (BRL) => Euro (EUR)
                5) Libra Esterlina (GBP) => Real brasileiro (BRL)
                6) Real brasileiro (BRL) => Libra Esterlina (GBP)
                7) Sair
                *******************************************************
                """;

        while (opcao != 7) {
            System.out.println(menu);
            System.out.print("Escolha uma opção válida: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                if (opcao >= 1 && opcao <= 6) {
                    System.out.print("Digite o valor que deseja converter: ");
                    String entradaUsuario = scanner.nextLine();

                    entradaUsuario = entradaUsuario.replace(",", ".");

                    double valor = Double.parseDouble(entradaUsuario);

                    realizarConversao(opcao, valor);
                } else if (opcao == 7) {
                    System.out.println("Saindo do Conversor de Moedas. Até logo!");
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }

        scanner.close();
    }

    private static void realizarConversao(int opcao, double valor) {
        String moedaBase = "";
        String moedaAlvo = "";

        switch (opcao) {
            case 1 -> { moedaBase = "USD"; moedaAlvo = "BRL"; }
            case 2 -> { moedaBase = "BRL"; moedaAlvo = "USD"; }
            case 3 -> { moedaBase = "EUR"; moedaAlvo = "BRL"; }
            case 4 -> { moedaBase = "BRL"; moedaAlvo = "EUR"; }
            case 5 -> { moedaBase = "GBP"; moedaAlvo = "BRL"; }
            case 6 -> { moedaBase = "BRL"; moedaAlvo = "GBP"; }
        }

        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + moedaBase + "/" + moedaAlvo + "/" + valor;

        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

            if (jsonObject.get("result").getAsString().equals("success")) {
                double resultadoFinal = jsonObject.get("conversion_result").getAsDouble();

                System.out.printf("\n>>> O valor %.2f [%s] corresponde ao valor final de => %.2f [%s] <<<\n",
                        valor, moedaBase, resultadoFinal, moedaAlvo);
            } else {
                System.out.println("Erro na API: " + jsonObject.get("error-type").getAsString());
            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a conversão: " + e.getMessage());
        }
    }
}