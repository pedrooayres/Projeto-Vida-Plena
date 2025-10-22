# 🌿 Projeto Vida Plena – Sistema Integrado

Um sistema unificado que conecta **Clínica**, **Eventos** e **Restaurante**, permitindo o gerenciamento inteligente de **pessoas, locais, agendas e pagamentos** em um só ambiente.

Desenvolvido como parte da disciplina de **Programação Orientada a Objetos (POO)**, o projeto aplica os princípios de **encapsulamento, herança, polimorfismo e modularização**, com código organizado em **pacotes Java**.

---

## 🚀 Funcionalidades Principais

✅ Cadastro e gerenciamento de **consultas médicas**, **eventos sociais** e **pedidos de restaurante**  
✅ Compartilhamento de dados entre módulos (pessoas, locais, agenda e pagamentos)  
✅ Sistema de **menu interativo** via console  
✅ Relatórios estratégicos automáticos gerados ao encerrar o sistema  
✅ Encapsulamento completo (`private`, `getters`, `setters`)  
✅ Estrutura de pacotes bem definida  
✅ Suporte a expansão modular (novos serviços podem ser adicionados facilmente)

---

## 🧩 Estrutura do Projeto

vidaplena/
├── clinica/
│ ├── Medico.java
│ ├── Paciente.java
│ ├── Consulta.java
│ └── ClinicaRelatorios.java
│
├── eventos/
│ ├── Evento.java
│ ├── Participante.java
│ └── EventoRelatorios.java
│
├── restaurante/
│ ├── Pedido.java
│ ├── Item.java
│ └── RestauranteRelatorios.java
│
├── compartilhado/
│ ├── Pessoa.java
│ ├── Local.java
│ ├── Pagamento.java
│ └── Agenda.java
│
├── relatorios/
│ └── RelatoriosGerais.java
│
└── Main.java


---

## 🧠 Perguntas Estratégicas Respondidas por Métodos
1- Quais médicos e eventos têm maior ocupação nos mesmos horários? (clinica + evento)
2 - Qual o dia com mais faltas na clinica e nos eventos (clinica + evento)
3- medicos/ evento mais requisitados (clinica + evento)
4 - Quais dias da semana têm maior concentração de atividades em todas as áreas? (clinica + evento + restuarente)
5- Qual tipo de serviço (clínico, evento ou restaurante) gera mais receita mensal? (clinica + evento + restuarante)
6- Qual faixa de horário é mais movimentada em todos os setores? (clinica + evento + restuarante)
7-Quais clientes possuem maior gasto total somando consultas, eventos e pedidos no restaurante? (evento + restuarante)
8-Quais datas apresentam maior volume de atividades simultâneas entre áreas diferentes?(clinica + evento +restuarante)
9-Qual é o percentual de comparecimento em relação às agendas criadas (consultas realizadas, eventos e reservas confirmadas)? (clinica + evento + restuarante)


---

## 🧱 Conceitos Aplicados

- **Encapsulamento:** todos os atributos são `private`, com acesso via getters e setters.  
- **Herança:** `Pessoa` é classe base para `Medico`, `Paciente` e `Participante`.  
- **Composição:** `Consulta`, `Evento` e `Pedido` utilizam `Local`, `Agenda` e `Pagamento`.  
- **Polimorfismo:** métodos de relatório que processam diferentes tipos de objetos.  
- **Modularização:** uso de pacotes Java para separar responsabilidades.  

---

## ⚙️ Como Executar

1. Abra o projeto em uma IDE Java (Eclipse, IntelliJ ou VSCode).  
2. Certifique-se de que o **JDK 17** (ou superior) está configurado.  
3. Compile e execute o arquivo `Main.java`.  
4. Use o menu interativo para cadastrar dados e gerar relatórios.  
5. Ao escolher **0 – Encerrar**, os relatórios finais são gerados automaticamente.

---

## 📊 Exemplo de Execução (Console)