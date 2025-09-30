# ToDoList Android App

Este é um aplicativo simples de lista de tarefas desenvolvido em Kotlin com Android Studio. Ele permite que o usuário adicione, visualize, remova e limpe tarefas, com persistência local usando `SharedPreferences`.

## 🛠 Funcionalidades

- ✅ Adicionar novas tarefas
- 🗑 Remover tarefas individualmente
- 🔄 Limpar toda a lista com confirmação
- 💾 Salvamento automático das tarefas
- 🎨 Personalização da cor da barra de status (Android Lollipop ou superior)

## 📦 Tecnologias Utilizadas

- Kotlin
- Android SDK
- RecyclerView
- SharedPreferences
- AlertDialog

## 📲 Como usar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/ToDoList.git

2. Abra o projeto no Android Studio

3. Execute em um emulador ou dispositivo físico


📁 Estrutura principal- MainActivity.kt: lógica principal da aplicação
- TarefaAdapter.kt: adapter para exibir tarefas na RecyclerView
- activity_main.xml: layout da interface

  
📌 Observações- As tarefas são armazenadas localmente, não há sincronização com nuvem.
- O projeto utiliza SharedPreferences para persistência simples.
   
