package com.example.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var botaoAdicionar: Button
    private lateinit var botaoLimpar: Button
    private lateinit var recyclerView: RecyclerView

    private val listaTarefas = mutableListOf<String>()
    private lateinit var adapter: TarefaAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.minha_cor)
        }

        editText = findViewById(R.id.editTextTarefa)
        botaoAdicionar = findViewById(R.id.botaoAdicionar)
        botaoLimpar = findViewById(R.id.botaoLimpar)
        recyclerView = findViewById(R.id.recyclerView)

        // Carregar tarefas salvas
        carregarTarefas()

        // Configurar RecyclerView
        adapter = TarefaAdapter(listaTarefas) { posicao ->
            removerTarefa(posicao)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Adicionar tarefa
        botaoAdicionar.setOnClickListener {
            val tarefa = editText.text.toString()
            if (tarefa.isNotBlank()) {
                listaTarefas.add(tarefa)
                salvarTarefas()
                adapter.notifyItemInserted(listaTarefas.size - 1)
                editText.text.clear()
                Toast.makeText(this, "Tarefa adicionada!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Digite uma tarefa", Toast.LENGTH_SHORT).show()
            }
        }

        // Limpar todas as tarefas
        botaoLimpar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Limpar lista")
                .setMessage("Tem certeza que deseja apagar todas as tarefas?")
                .setPositiveButton("Sim") { _, _ ->
                    listaTarefas.clear()
                    salvarTarefas()
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Lista limpa!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    private fun removerTarefa(posicao: Int) {
        listaTarefas.removeAt(posicao)
        salvarTarefas()
        adapter.notifyItemRemoved(posicao)
        Toast.makeText(this, "Tarefa removida!", Toast.LENGTH_SHORT).show()
    }

    // Salvar no SharedPreferences
    private fun salvarTarefas() {
        val prefs = getSharedPreferences("ToDoList", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putStringSet("tarefas", listaTarefas.toSet())
        editor.apply()
    }

    // Carregar do SharedPreferences
    private fun carregarTarefas() {
        val prefs = getSharedPreferences("ToDoList", Context.MODE_PRIVATE)
        val tarefasSalvas = prefs.getStringSet("tarefas", setOf()) ?: setOf()
        listaTarefas.addAll(tarefasSalvas)
    }
}
