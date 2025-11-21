# 📚 Jornada do Dev

## 📖 Sobre o Projeto

**Jornada do Dev** é um jogo de simulação de carreira em desenvolvimento de software, onde o jogador evolui desde estudante até CEO, realizando atividades que aumentam suas habilidades técnicas e interpessoais.

O jogador começa como **Estudante** e progride através de níveis (Estagiário, Júnior, Pleno, Sênior, Tech Lead, CEO), completando atividades que concedem XP e aprimoram habilidades específicas.

---

## 🎯 Conceitos de POO Aplicados

### 1️⃣ **Coleções** ✅

O projeto utiliza `ArrayList` extensivamente para gerenciar diferentes elementos do jogo:

#### **Arquivo: `Models/Level.java`**
```java
private final ArrayList<Activity> activities;
```
- Cada nível armazena uma lista de atividades disponíveis

#### **Arquivo: `Models/Activity.java`**
```java
private ArrayList<Skill> skill_point_gain;
```
- Cada atividade possui uma lista de habilidades que o jogador ganhará ao completá-la

#### **Arquivo: `System/Controller.java`**
```java
ArrayList<Activity> Current_level_activity_list = level_current.getActivities();
```
- Controller manipula listas de atividades durante a execução das tarefas

#### **Arquivo: `Models/Player.java`**
```java
public List<Activity> getAvailableActivities() {
    return levelData.getActivities();
}
```
- Retorna lista de atividades disponíveis baseada no nível atual

### 2️⃣ **Tratamento de Exceções** ✅

#### **Arquivo: `System/Exceptions.java`**
```java
static public int InputInterger(Scanner input){
    int numero;
    while(true){
        try{
            return input.nextInt();
        } catch (InputMismatchException e){
            GUI.ShowMessageAlert("Digite apenas números!");
            input.nextLine();
        }
    }
}
```
**Tratamento:** Captura `InputMismatchException` quando o usuário digita texto em vez de número.

#### **Arquivo: `Utils/Structure/Support.java`**
```java
try {
    TimeUnit.SECONDS.sleep(time);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    System.out.println("Erro" + e.getMessage());
}
```
**Tratamento:** Captura `InterruptedException` durante operações de sleep.

#### **Arquivo: `Exceptions.java` - Abertura de Links**
```java
static public void OpenLinkStardewVally(){
    try{
        URI link = new URI("https://pt.stardewvalleywiki.com/Stardew_Valley_Wiki");
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(link);
        } else {
            System.out.println("Não é possível abrir o navegador no seu sistema.");
        }
    }catch (Exception e){
        System.out.println("Erro para acessar" + e.getMessage() );
    }
}
```
**Tratamento:** Captura exceções ao tentar abrir URLs externas no navegador.

**Casos tratados:**
- ✅ Entrada inválida de números
- ✅ Erros de limpeza de tela
- ✅ Interrupção de threads
- ✅ Falhas ao abrir links externos

---

### 3️⃣ **Polimorfismo** ✅

#### **Arquivo: `Models/Character.java` (Classe Abstrata)**
```java
public abstract class Character {
    private String name;
    private String occupation;
    
    public Character(String nome, String occupation) {
        this.name = nome;
        this.occupation = occupation;
    }
}
```

#### **Arquivo: `Models/Player.java` (Herda de Character)**
```java
public class Player extends Character {
    private int level = 1;
    private double xp;
    // ... atributos específicos do jogador
    
    public Player(String nome, int num_level, double xp, Level level1) {
        super(nome, level1.name()); // Chama construtor da superclasse
        this.level = num_level;
        this.xp = xp;
    }
}
```

**Polimorfismo aplicado:**
- `Player` é uma especialização de `Character`
- `Character` define estrutura base (nome, ocupação)
- `Player` adiciona comportamentos específicos (XP, nível, habilidades)
- Permite extensão futura: `NPC`, `Mentor`, `Boss` poderiam herdar de `Character`

**Sobrescrita de métodos:**
```java
@Override
public String toString() {
    return "Nome: " + this.name + "Cargo: " + this.occupation;
}
```

---

### 4️⃣ **Abstração** ✅

A abstração está presente em várias classes que modelam elementos do mundo real:

#### **Arquivo: `Models/Activity.java`**
```java
public class Activity {
    private String name;
    private String descricao;
    private double activity_value;
    private int state;
    private boolean obligatory;
    private ArrayList<Skill> skill_point_gain;
}
```
**Abstrai:** Atividades profissionais reais (programar, revisar código, participar de reuniões)

#### **Arquivo: `Models/Skill.java`**
```java
public class Skill {
    private int indice;
    private String name;
    private int pont_skills;
}
```
**Abstrai:** Competências técnicas e soft skills do mercado de TI

#### **Arquivo: `Models/Level.java` (Enum)**
```java
public enum Level {
    ESTUDANTE(1, "ESTUDANTE", 0, 50, activities),
    ESTAGIARIO(2, "ESTAGIÁRIO", 50, 125, activities),
    JUNIOR(3, "JÚNIOR", 125, 200, activities),
    // ...
}
```
**Abstrai:** Hierarquia de cargos na carreira de desenvolvimento de software

#### **Arquivo: `Models/Player.java`**
```java
public void levelUp() {
    level++;
    xp = 0;
    xpNeeded = xpNeeded * 1.5;
    updateLevelData();
    updateOccupation();
}
```
**Abstrai:** Progressão de carreira e evolução profissional

**Elementos abstraídos:**
- ✅ Atividades profissionais
- ✅ Habilidades técnicas
- ✅ Níveis de senioridade
- ✅ Sistema de progressão por experiência

---

### 5️⃣ **Encapsulamento** ✅

Todos os atributos são `private` com acesso controlado por getters/setters:

#### **Arquivo: `Models/Player.java`**
```java
private int level = 1;
private double xp;
private int network_quantity;
private int communication;
// ... outros atributos privados

// Getters e Setters
public int getLevel() { return level; }
public void setLevel(int level) { 
    this.level = level;
    updateLevelData();
    updateOccupation();
}

public double getXp() { return xp; }
public void setXp(double xp) { this.xp = xp; }
```

#### **Arquivo: `Models/Activity.java`**
```java
private String name;
private double activity_value;
private ArrayList<Skill> skill_point_gain;

public String getName() { return name; }
public void setName(String name) { this.name = name; }

public double getActivity_value() { return activity_value; }
public void setActivity_value(double activity_value) { 
    this.activity_value = activity_value; 
}
```

#### **Arquivo: `Models/Character.java`**
```java
private String name;
private String occupation;

public String getName() { return name; }
public void setName(String nome) { this.name = nome; }

public String getOccupation() { return occupation; }
public void setOccupation(String occupation) { 
    this.occupation = occupation; 
}
```

#### **Arquivo: `Models/Level.java`**
```java
private final int level;
private final String name;
private final int xpRequired;
private final ArrayList<Activity> activities;

// Apenas getters (atributos finais não podem ser alterados)
public int getLevel() { return level; }
public String getName() { return name; }
public ArrayList<Activity> getActivities() { return activities; }
```

---

## 🎁 Sistema de Final Alternativo (Easter Egg)

O jogo possui um **final alternativo secreto** inspirado em **Stardew Valley**, implementado através do sistema de probabilidade.

### 🎲 **Como Funciona**

#### **Arquivo: `System/Controller.java`**
```java
static public boolean Probability_Showing_Letter(String name){
    boolean choose = false;
    int num_rand = rand.nextInt(1) + 1; // Gera número de 1 a 1
    int num_acerto = 1;

    if(num_rand == num_acerto){
        choose = GUI.showCarta(name);
    }

    return choose;
}
```

**Mecânica:**
- A cada atividade completada, há uma chance **extremamente rara** de aparecer uma carta misteriosa
- O sistema gera um número aleatório e compara com o número de acerto
- Se houver match, a interface gráfica exibe a carta da vovó

### 📜 **A Carta da Vovó**

#### **Arquivo: `Utils/Menu/GUI.java`**
```java
public static boolean showCarta(String nome) {
    AtomicBoolean go_place = new AtomicBoolean(false);
    
    JFrame frame = new JFrame("Convite");
    // ... criação da interface gráfica
    
    JLabel labelInformation = new JLabel(
        "<html>" +
        "Se você está lendo isso, significa que você está desesperado por uma mudança na sua vida.<br><br>" +
        "Há muito tempo atrás, o mesmo aconteceu comigo. Eu perdi de vista o que mais importa na vida: " +
        "vínculos concretos com a natureza e outras pessoas. Foi então que decidi largar tudo e me mudar " +
        "para o meu verdadeiro lar.<br><br>" +
        "Em anexo você encontrará a escritura para esse lugar… meu orgulho e alegria: O Sítio do Pica-pau Amarelo.<br>" +
        "Ele fica no Vale do Orvalho, no litoral sul. É perfeito para começar a sua nova vida. " +
        "O meu bem mais precioso agora é seu. Eu sei que você vai honrar a nossa família, meu garoto. Boa sorte.<br><br>" +
        "Com amor,<br>" +
        "Vovó<br><br>" +
        "P.S.: Se o Lewis ainda estiver vivo, mande um Oi pelo seu corôa, tá?" +
        "</html>"
    );
    
    // Retorna true se jogador aceitar ir para o sítio
    return go_place.get();
}
```

**Elementos da Interface:**
- ✉️ **Design temático:** Fundo escuro com bordas verdes
- 🎨 **Estilização:** Uso de HTML no JLabel para formatação rica de texto
- 🖱️ **Duas opções interativas:**
  - **"Ir para o Sítio"** - Botão verde que aceita o convite
  - **"Continuar na Carreira de Programador"** - Botão vermelho que recusa

### 🌾 **Desfecho Alternativo**

#### **Arquivo: `System/MainProgram.java`**
```java
do {
    CLI.Lobby_Information(controller.player);
    CLI.Show_Activitys(controller.level_current.getActivities());
    controller.Choose_Activity();
    go_place = Controller.Probability_Showing_Letter(controller.player.getName());
} while(controller.player.getLevel() < 7 && !go_place);

if(go_place){
    GUI.ShowMessageAlert("VOCE ESCOLHEU O FINAL ALTERNATIVO");
    Exceptions.OpenLinkStardewVally();
} else {
    GUI.ShowMessageAlert("PARABENS CEO!");
}
```

**Fluxo do Final Alternativo:**
1. 🎲 Durante o jogo, a carta pode aparecer aleatoriamente
2. 📖 O jogador lê a história emocionante da vovó
3. 🤔 Deve escolher entre **continuar programando** ou **ir para o sítio**
4. 🌾 Se aceitar, o jogo **termina antecipadamente**
5. 🌐 O navegador abre automaticamente no **Stardew Valley Wiki**

#### **Arquivo: `System/Exceptions.java`**
```java
static public void OpenLinkStardewVally(){
    try{
        URI link = new URI("https://pt.stardewvalleywiki.com/Stardew_Valley_Wiki");
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(link);
        } else {
            System.out.println("Não é possível abrir o navegador no seu sistema.");
        }
    }catch (Exception e){
        System.out.println("Erro para acessar" + e.getMessage() );
    }
}
```

### 📊 **Diagrama de Fluxo do Easter Egg**

Jogador completa atividade
         ↓
Probability_Showing_Letter()
         ↓
    Sorteio (1/100)
         ↓
   ┌─────┴─────┐
   │           │
Falha       Sucesso
   │           │
   │      showCarta()
   │           │
   │    ┌──────┴──────┐
   │    │             │
   │  Recusa       Aceita
   │    │             │
   └────┤        go_place = true
        │             │
   Continua    OpenLinkStardewVally()
    Jogo            ↓
                Fim do Jogo

---

## 🗂️ Estrutura do Projeto
```
📦 Jornada do Dev
├── 📁 Models/
│   ├── Activity.java       # Representa atividades disponíveis
│   ├── Character.java      # Classe abstrata base
│   ├── Player.java         # Jogador (herda de Character)
│   ├── Skill.java          # Habilidades técnicas
│   └── Level.java          # Enum com níveis de carreira
├── 📁 System/
│   ├── Controller.java     # Controlador principal do jogo
│   ├── Exceptions.java     # Tratamento de exceções
│   └── MainProgram.java    # Ponto de entrada
├── 📁 Utils/
│   ├── Menu/
│   │   ├── CLI.java        # Interface de linha de comando
│   │   └── GUI.java        # Interface gráfica (Swing)
│   └── Structure/
│       └── Support.java    # Funções auxiliares
└── 📁 legacy/              # Código antigo (não usado)
```

---

## 🔍 Screenshots

### **Interface de Introdução**
![Interface de Introdução](Screenshot_da_interface_introdutoria.png)

### **Convite da Vovó (Easter Egg)**
![Convite](Screenshots_do_convite.png)

### **Interface de Interação**
![Interface de Interação](Screeshot_interface_de_Interacao.png)

---

## 🎮 Como Jogar

### **Início**
1. Digite seu nome
2. Leia a história introdutória
3. Comece no nível **ESTUDANTE**

### **Gameplay**
1. Veja suas estatísticas (XP, habilidades)
2. Escolha uma atividade disponível
3. Complete a atividade para ganhar:
   - 🎯 XP (experiência)
   - 💪 Pontos de habilidade
4. Suba de nível ao acumular XP suficiente
5. Desbloqueie novas atividades
6. 🎁 **Fique atento à carta misteriosa da vovó!**

### **Habilidades**
- 🌐 **Network** - Conexões profissionais
- 💬 **Comunicação** - Soft skills
- 🧩 **Algoritmos** - Estruturas de dados
- 🗄️ **Bancos de Dados** - SQL e NoSQL
- 🧠 **Pensamento Lógico** - Resolução de problemas
- 🏗️ **Arquitetura de Software** - Design de sistemas

---

## 🚀 Níveis de Carreira

| Nível | Cargo | XP Necessário | Atividades |
|-------|-------|---------------|------------|
| 1 | ESTUDANTE | 0 | Praticar lógica, estudar tutoriais |
| 2 | ESTAGIÁRIO | 50 | Corrigir bugs, documentar código |
| 3 | JÚNIOR | 125 | Implementar APIs, criar testes |
| 4 | PLENO | 200 | Otimizar performance, CI/CD |
| 5 | SÊNIOR | 275 | Arquitetar microsserviços, RCA |
| 6 | TECH LEAD | 350 | Liderar time, definir roadmap |
| 7 | CEO | 425 | Estratégia empresarial, IPO |

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 24
- **GUI:** Java Swing
- **Estruturas:** ArrayList, Enum, Random, AtomicBoolean
- **APIs:** Desktop (java.awt), URI
- **IDE:** IntelliJ IDEA

---

## 📊 Fluxo do Jogo
```
Início
  ↓
Criar Jogador
  ↓
Nível 1: ESTUDANTE
  ↓
Escolher Atividade → Executar Atividade → Ganhar XP + Skills
  ↓                                              ↓
  ←──────────────────────────────────────────────┘
  ↓
Verificar Carta da Vovó (Easter Egg)
  ↓
  ├─→ Aceita Convite → Fim Alternativo (Stardew Valley)
  │
  └─→ Recusa/Não Aparece
       ↓
XP >= XP Necessário? → Level Up! → Nível < 7?
  |                                       ↓
  |                                    Continuar
  ↓
Fim do Jogo (CEO)
```

## 🎯 Exemplos de Atividades 

### **Nível ESTUDANTE**
- ✏️ Praticar lógica de programação (+5 XP)
- 📚 Fazer revisão de disciplinas (+3 XP)
- 👥 Participar de grupo de estudos (+4 XP, Buff 2x)

### **Nível SÊNIOR**
- 🏗️ Definir arquitetura de microsserviços (+20 XP)
- 🔍 Realizar análise de incidente com RCA (+18 XP)
- 📝 Escrever ADRs técnicos (+16 XP)

---

## 👨‍💻 Autores

Alunos da **UNIVERSIDADE CATÓLICA DE PERNAMBUCO - UNICAP**: 
- Inaldo José do Nascimento
- Lucas da Silva Soares

