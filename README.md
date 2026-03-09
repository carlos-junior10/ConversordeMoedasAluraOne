# 🪙 Conversor de Moedas - Desafio Java

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![API](https://img.shields.io/badge/API-ExchangeRate-blue?style=for-the-badge)

## 📝 Descrição
O **Conversor de Moedas** é uma aplicação desenvolvida em Java com interação via console. Este projeto consome a ExchangeRate-API para obter taxas de câmbio em tempo real e utiliza a biblioteca Gson para a extração e manipulação dos dados em formato JSON. O sistema oferece um menu interativo para que o utilizador escolha o par de moedas e o valor que deseja converter.

## ⚙️ Funcionalidades
O sistema realiza conversões em tempo real das seguintes moedas:
- Dólar (USD) para Real (BRL)
- Real (BRL) para Dólar (USD)
- Euro (EUR) para Real (BRL)
- Real (BRL) para Euro (EUR)
- Libra Esterlina (GBP) para Real (BRL)
- Real (BRL) para Libra Esterlina (GBP)

**Destaque Técnico:** Tratamento inteligente de entrada de dados, permitindo ao utilizador introduzir valores decimais utilizando tanto a vírgula (padrão local) como o ponto (padrão americano), evitando quebras na execução (NumberFormatException).

## 🛠️ Tecnologias Utilizadas
- **Java**: Linguagem principal do projeto, utilizando as classes nativas `HttpClient`, `HttpRequest` e `HttpResponse` para comunicação com APIs.
- **Gson (Google)**: Biblioteca utilizada para converter (parsear) a resposta da API de formato String (JSON) para objetos manipuláveis.
- **ExchangeRate-API**: API REST que fornece taxas de câmbio atualizadas, utilizada para obter os valores de conversão entre diferentes moedas.

