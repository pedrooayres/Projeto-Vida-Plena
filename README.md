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

```
src/
├── clinica/
│   ├── Clinica.java
│   ├── Consulta.java
│   ├── ClinicaRelatorios.java
│
├── eventos/
│   ├── Evento.java
│   ├── Participante.java
│   ├── EventosRelatorios.java
│
├── restaurante/
│   ├── Pedido.java
│   ├── RestauranteRelatorios.java
│
├── compartilhado/
│   ├── Pessoa.java
│   ├── Local.java
│   ├── Pagamento.java
│
└── Main.java
```

---

## 🔍 Perguntas de Tomada de Decisão

Durante o desenvolvimento, o grupo criou **9 perguntas estratégicas** que cruzam dados entre os três sistemas, permitindo gerar *relatórios inteligentes* e auxiliar na tomada de decisões:

| # | Pergunta | Sistemas Envolvidos |
|:-:|-----------|---------------------|
| 1 | Quais médicos e eventos têm maior ocupação nos mesmos horários? | Clínica + Eventos |
| 2 | Qual o dia com mais faltas na clínica e nos eventos? | Clínica + Eventos |
| 3 | Médicos/eventos mais requisitados. | Clínica + Eventos |
| 4 | Quais dias da semana têm maior concentração de atividades em todas as áreas? | Clínica + Eventos + Restaurante |
| 5 | Qual tipo de serviço gera mais receita mensal? | Clínica + Eventos + Restaurante |
| 6 | Qual faixa de horário é mais movimentada em todos os setores? | Clínica + Eventos + Restaurante |
| 7 | Quais clientes possuem maior gasto total somando consultas, eventos e pedidos? | Clínica + Eventos + Restaurante |
| 8 | Quais datas apresentam maior volume de atividades simultâneas? | Clínica + Eventos + Restaurante |
| 9 | Qual o percentual de comparecimento em relação às agendas criadas? | Clínica + Eventos + Restaurante |

---

## 💻 Como Executar o Projeto

### 🔧 Requisitos
- **Java 17+**
- IDE (IntelliJ, VS Code, Eclipse) ou terminal com `javac` configurado.

### ▶️ Execução via terminal
1. Acesse o diretório raiz do projeto:
   ```bash
   cd Projeto-Vida-Plena/src
   ```
2. Compile os arquivos:
   ```bash
   javac main/Main.java
   ```
3. Execute o programa:
   ```bash
   java main.Main
   ```

---

## 📊 Diagrama de Classes (Resumo UML)

*(Adicione aqui uma imagem exportada do Lucidchart / draw.io / PlantUML)*  
Exemplo:
```
Pessoa <|-- Paciente
Pessoa <|-- Participante
Pessoa <|-- Cliente
Clinica --> Consulta
Eventos --> Evento
Restaurante --> Pedido
```

---

## 🎭 Equipe Vida Plena — Halloween Edition

| Integrante | Módulo | Função |
|-------------|--------|--------|
| 🧠 **Pedro Henrique Mendonça Ayres** | Eventos | Relatórios e integração com clínica/restaurante |
| 💉 **[Nome do colega 1]** | Clínica | Consultas, pacientes e relatórios |
| 🍽️ **[Nome do colega 2]** | Restaurante | Pedidos, faturamento e relatórios |

📅 **Apresentação:** *31 de outubro de 2025 — Halloween*  
💬 *Que os bugs não te assustem e os testes rodem à meia-noite!* 🦇  

---

## 🕯️ Créditos Especiais

Projeto desenvolvido para a disciplina **Programação Orientada a Objetos (POO)** —  
**Curso de Ciência da Computação - UNICAP**  
Professor: *[Nome do professor]*

---

## 👻 Mensagem Final

> “Entre consultas e feitiços, relatórios e poções,  
> o sistema Vida Plena está vivo... e rodando!” ⚰️
