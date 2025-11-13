<p align="center">
  <img src="https://github.com/user-attachments/assets/a8d8b7f0-efed-4227-9302-17a234f065c2" alt="Logo Projeto Vida Plena" width="300">
</p>

# 🧩 Projeto Vida Plena

O **Projeto Vida Plena** é um sistema modular desenvolvido em **Java**, voltado à gestão de uma clínica de saúde integrada, com módulos para:

- Clínica médica 🏥  
- Eventos 💬  
- Restaurante 🍽️  
- Relatórios 📊  

---

## 🗂️ Estrutura de Pacotes

Base/
├── Agenda.java
├── CarregadorDeDados.java
├── Local.java
├── Pagamento.java
├── Pessoa.java
│
├── Clinica/
│ ├── ClinicaRelatorios.java
│ ├── Consulta.java
│ ├── Medico.java
│ └── Paciente.java
│
├── Eventos/
│ ├── Evento.java
│ ├── EventosRelatorios.java
│ └── Participante.java
│
├── Restaurante/
│ ├── Pedido.java
│ ├── Prato.java
│ └── RestauranteRelatorios.java
│
└── Relatorios/
└── RelatoriosGerais.java

yaml
Copiar código

---

## 📊 Diagrama UML

<p align="center">
  <img src="https://github.com/user-attachments/assets/16a02f77-0d3a-4cf4-97e8-840925d4f680" 
       alt="Diagrama UML do Projeto Vida Plena" 
       width="800">
</p>

---

## 🖼️ Telas do Menu

<img src="https://github.com/user-attachments/assets/288ac60a-da2c-405a-b20f-23ee6ac38359" width="300">
<p><i>Imagem 1 –Relatório de percentual de comparecimento </i></p>

<img src="https://github.com/user-attachments/assets/1d7a82fb-ac3f-4650-b1b0-18146b5a64b4" width="300">
<p><i>Imagem 2 – Relatório de consumo dos clientes no evento x clinica</i></p>

<img src="https://github.com/user-attachments/assets/2d022ac8-547a-4dfb-9a28-f09c3a2f4455" width="300">
<p><i>Imagem 3 –Relatório de médicos e eventos que possuem choque </i></p>

<img src="https://github.com/user-attachments/assets/13714a09-1f6a-4ec3-874f-7247f112f8dd" width="300">
<p><i>Imagem 4 – Menu do Sistema : Perguntas</i></p>

<img src="https://github.com/user-attachments/assets/d591afba-ba0c-41ae-86b1-0448a1ccd935" width="300">
<p><i>Imagem 5 – Exemplo de criação de evento</i></p>

---

## ⚙️ Execução

Para compilar e executar o projeto:

```bash
javac Main.java
java Main
Ou, se estiver em uma IDE (VS Code, IntelliJ, Eclipse), basta rodar a classe Main.java.

👨‍💻 Tecnologias Utilizadas
Linguagem: Java 17+

Paradigma: Programação Orientada a Objetos (POO)

IDE Recomendada: VS Code / IntelliJ IDEA

Controle de Versão: Git + GitHub

📜 Licença
Este projeto está licenciado sob a licença MIT — você pode utilizá-lo e modificá-lo livremente, desde que mantenha os créditos aos autores.

✨ Créditos
Integrante	Módulo	Função
🧠 Pedro Henrique Mendonça Ayres	Eventos	Relatórios e integração com clínica/restaurante
💉 Diogo Fonseca	Clínica	Consultas, pacientes e relatórios
🍽️ João Gabriel	Restaurante	Pedidos, faturamento e relatórios

<p align="center"> <em>“Entre consultas e códigos, o sistema Vida Plena está vivo... e rodando!” ⚙️</em> </p> ```
