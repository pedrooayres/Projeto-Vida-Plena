<p align="center">
  <img src="logo_projeto.png" alt="Logo Projeto Vida Plena" width="800"/>
</p>

# 🎃 Projeto Vida Plena — Edição Especial de Halloween 👻

Bem-vindo ao sistema **Vida Plena**, desenvolvido para integrar **Clínica**, **Eventos** e **Restaurante** durante a *Semana Vida Plena* em Recife — um evento dedicado à **saúde, cultura e bem-estar**.  

🕸️ Nesta edição especial de **31 de outubro**, o projeto ganhou um toque misterioso…  
Entre consultas, oficinas e refeições, algo *assustadoramente funcional* está prestes a acontecer! 💀

---

## 🩺 Sobre o Projeto

O **Vida Plena** é composto por **três sistemas integrados**:

- 💉 **Clínica** – realiza consultas e check-ups rápidos.  
- 🎭 **Eventos** – organiza palestras, oficinas e shows de saúde e bem-estar.  
- 🍲 **Restaurante Parceiro** – oferece cardápios funcionais e combos especiais.

Os três sistemas **compartilham informações** sobre:
- 👥 Pessoas  
- 📍 Locais  
- 🗓️ Agendas  
- 💰 Pagamentos  

Tudo foi desenvolvido aplicando os princípios de **Programação Orientada a Objetos (POO)** em **Java**, com foco em **modularização, encapsulamento e integração entre módulos**.

---

## ⚙️ Estrutura do Projeto

src/
├── clinica/
│ ├── Clinica.java
│ ├── Consulta.java
│ ├── ClinicaRelatorios.java
│
├── eventos/
│ ├── Evento.java
│ ├── Participante.java
│ ├── EventosRelatorios.java
│
├── restaurante/
│ ├── Pedido.java
│ ├── RestauranteRelatorios.java
│
├── compartilhado/
│ ├── Pessoa.java
│ ├── Local.java
│ ├── Pagamento.java
│
└── Main.java
---

## 🔍 Perguntas de Tomada de Decisão

Durante o desenvolvimento, o grupo criou **9 perguntas estratégicas** que cruzam dados entre os três sistemas, permitindo gerar *relatórios inteligentes* e auxiliar na tomada de decisões:

| # | Pergunta | Sistemas Envolvidos |
|:-:|-----------|---------------------|
| 1 | Quais médicos e eventos têm maior ocupação no mesmo dia? | Clínica + Eventos |
| 2 | Algum paciente que faltou na clínica compareceu ao evento? | Clínica + Eventos |
| 3 | O cliente da clínica que visita pelo menos um evento, tem um gasto maior na clínica ou no evento? | Clínica + Eventos |
| 4 | Um médico da clínica fez a recomendação de um prato do restaurante — após esse dia, quantos pedidos desse prato foram feitos? | Clínica + Restaurante |
| 5 | Qual tipo de serviço (clínico, evento ou restaurante) gera mais receita mensal? | Todos |
| 6 | Qual o horário mais frequentado no restaurante e consultório? | Clínica + Restaurante |
| 7 | Em determinado evento com participação do restaurante, qual o preço médio dos pedidos nesse dia? | Eventos + Restaurante |
| 8 | Quais clientes estão envolvidos em mais de um serviço? | Todos |
| 9 | Qual é o percentual de comparecimento em relação às agendas criadas (consultas realizadas e eventos confirmados)? | Clínica + Eventos |

---

## 💻 Como Executar o Projeto

### 🔧 Requisitos
- **Java 17+**
- IDE (IntelliJ, VS Code, Eclipse) ou terminal com `javac` configurado.

### ▶️ Execução via terminal
```bash
cd Projeto-Vida-Plena/src
javac main/Main.java
java main.Main

## 🧩 Diagrama UML — Estrutura Geral do Projeto
<p align="center">
  <img src="diagrama_UML_PVPR.png" alt="Diagrama UML do Projeto Vida Plena" width="850"/>
</p>
## 🖼️ Capturas de Tela da Execução
<p align="center"><img src="imagem_menu_1.png" alt="Execução do Projeto Vida Plena" width="800"/></p>
<p align="center"><img src="imagem_menu_2.png" alt="Execução do Projeto Vida Plena" width="800"/></p>
<p align="center"><img src="imagem_menu_3.png" alt="Execução do Projeto Vida Plena" width="800"/></p>
<p align="center"><img src="imagem_menu_4.png" alt="Execução do Projeto Vida Plena" width="800"/></p>
<p align="center"><img src="imagem_menu_5.png" alt="Execução do Projeto Vida Plena" width="800"/></p>

🎭 Equipe Vida Plena — Halloween Edition
Integrante	Módulo	Função
🧠 Pedro Henrique Mendonça Ayres	Eventos	Relatórios e integração com clínica/restaurante
💉 Diogo Fonseca	Clínica	Consultas, pacientes e relatórios
🍽️ João Gabriel	Restaurante	Pedidos, faturamento e relatórios

📅 Apresentação: 31 de outubro de 2025 — Halloween
💬 Que os bugs não te assustem e os testes rodem à meia-noite! 🦇

🕯️ Créditos Especiais
Projeto desenvolvido para a disciplina Programação Orientada a Objetos (POO) —
Curso de Ciência da Computação - UNICAP
Professor: Lucas Rodolfo

👻 Mensagem Final
“Entre consultas e feitiços, relatórios e poções,
o sistema Vida Plena está vivo... e rodando!” ⚰️
