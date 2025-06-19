package com.example.sanpedrito

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tilNombre: TextInputLayout
    private lateinit var etNombre: TextInputEditText
    private lateinit var tilApellidos: TextInputLayout
    private lateinit var etApellidos: TextInputEditText
    private lateinit var tilCodigo: TextInputLayout
    private lateinit var etCodigo: TextInputEditText
    private lateinit var tilEmail: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var tilTelefono: TextInputLayout
    private lateinit var etTelefono: TextInputEditText
    private lateinit var spinnerTipo: Spinner
    private lateinit var spinnerFacultad: Spinner
    private lateinit var spinnerEscuela: Spinner
    private lateinit var chipGroupTalla: ChipGroup
    private lateinit var chipGroupParticipacion: ChipGroup
    private lateinit var btnRegistrar: MaterialButton
    private lateinit var btnLimpiar: MaterialButton
    private lateinit var cardView: MaterialCardView
    private lateinit var tvTitulo: TextView
    private lateinit var tvEvento: TextView
    private lateinit var tvFecha: TextView

    private val participantes = mutableListOf<Participante>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar ActionBar
        supportActionBar?.title = "San Pedrito UNS 2025"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        setupSpinners()
        setupChips()
        setupButtons()
        setupContextMenu()

        // Configurar colores de la UNS
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.white)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.uns_red)
    }

    private fun initViews() {
        tilNombre = findViewById(R.id.tilNombre)
        etNombre = findViewById(R.id.etNombre)
        tilApellidos = findViewById(R.id.tilApellidos)
        etApellidos = findViewById(R.id.etApellidos)
        tilCodigo = findViewById(R.id.tilCodigo)
        etCodigo = findViewById(R.id.etCodigo)
        tilEmail = findViewById(R.id.tilEmail)
        etEmail = findViewById(R.id.etEmail)
        tilTelefono = findViewById(R.id.tilTelefono)
        etTelefono = findViewById(R.id.etTelefono)
        spinnerTipo = findViewById(R.id.spinnerTipo)
        spinnerFacultad = findViewById(R.id.spinnerFacultad)
        spinnerEscuela = findViewById(R.id.spinnerEscuela)
        chipGroupTalla = findViewById(R.id.chipGroupTalla)
        chipGroupParticipacion = findViewById(R.id.chipGroupParticipacion)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        cardView = findViewById(R.id.cardView)
        tvTitulo = findViewById(R.id.tvTitulo)
        tvEvento = findViewById(R.id.tvEvento)
        tvFecha = findViewById(R.id.tvFecha)
    }

    private fun setupSpinners() {
        // Spinner Tipo de Participante
        val tipoAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.tipo_participante,
            android.R.layout.simple_spinner_item
        )
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipo.adapter = tipoAdapter

        // Spinner Facultad
        val facultadAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.facultades,
            android.R.layout.simple_spinner_item
        )
        facultadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFacultad.adapter = facultadAdapter

        // Spinner Escuela
        val escuelaAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.escuelas_sistemas,
            android.R.layout.simple_spinner_item
        )
        escuelaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEscuela.adapter = escuelaAdapter

        // Listener para cambiar escuelas según facultad
        spinnerFacultad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val escuelaArrayId = when (position) {
                    0 -> R.array.escuelas_sistemas // Ingeniería
                    1 -> R.array.escuelas_educacion
                    2 -> R.array.escuelas_medicina
                    else -> R.array.escuelas_sistemas
                }

                val adapter = ArrayAdapter.createFromResource(
                    this@MainActivity,
                    escuelaArrayId,
                    android.R.layout.simple_spinner_item
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerEscuela.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupChips() {
        // Chips para talla
        val tallas = arrayOf("XS", "S", "M", "L", "XL", "XXL")
        tallas.forEach { talla ->
            val chip = Chip(this)
            chip.text = talla
            chip.isCheckable = true
            chipGroupTalla.addView(chip)
        }

        // Chips para tipo de participación
        val participaciones = arrayOf("Desfile", "Organización", "Ambos")
        participaciones.forEach { participacion ->
            val chip = Chip(this)
            chip.text = participacion
            chip.isCheckable = true
            chipGroupParticipacion.addView(chip)
        }

        // Configurar single selection para talla
        chipGroupTalla.isSingleSelection = true
        chipGroupParticipacion.isSingleSelection = true
    }

    private fun setupButtons() {
        btnRegistrar.setOnClickListener {
            if (validarFormulario()) {
                registrarParticipante()
            }
        }

        btnLimpiar.setOnClickListener {
            limpiarFormulario()
        }
    }

    private fun setupContextMenu() {
        registerForContextMenu(cardView)
        registerForContextMenu(tvTitulo)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_info -> {
                mostrarInfoEvento()
                true
            }
            R.id.action_compartir -> {
                compartirApp()
                true
            }
            R.id.action_contacto -> {
                mostrarContacto()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_lista -> {
                mostrarListaParticipantes()
                true
            }
            R.id.action_estadisticas -> {
                mostrarEstadisticas()
                true
            }
            R.id.action_web -> {
                abrirPaginaWeb()
                true
            }
            R.id.action_exportar -> {
                exportarDatos()
                true
            }
            R.id.action_configuracion -> {
                abrirConfiguracion()
                true
            }
            R.id.action_acerca -> {
                mostrarAcercaDe()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun validarFormulario(): Boolean {
        var esValido = true

        // Validar nombre
        if (etNombre.text.toString().trim().isEmpty()) {
            tilNombre.error = "El nombre es obligatorio"
            esValido = false
        } else {
            tilNombre.error = null
        }

        // Validar apellidos
        if (etApellidos.text.toString().trim().isEmpty()) {
            tilApellidos.error = "Los apellidos son obligatorios"
            esValido = false
        } else {
            tilApellidos.error = null
        }

        // Validar código
        if (etCodigo.text.toString().trim().isEmpty()) {
            tilCodigo.error = "El código es obligatorio"
            esValido = false
        } else if (etCodigo.text.toString().trim().length < 6) {
            tilCodigo.error = "El código debe tener al menos 6 caracteres"
            esValido = false
        } else {
            tilCodigo.error = null
        }

        // Validar email
        val email = etEmail.text.toString().trim()
        if (email.isEmpty()) {
            tilEmail.error = "El email es obligatorio"
            esValido = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Email inválido"
            esValido = false
        } else {
            tilEmail.error = null
        }

        // Validar teléfono
        val telefono = etTelefono.text.toString().trim()
        if (telefono.isEmpty()) {
            tilTelefono.error = "El teléfono es obligatorio"
            esValido = false
        } else if (telefono.length < 9) {
            tilTelefono.error = "Teléfono inválido"
            esValido = false
        } else {
            tilTelefono.error = null
        }

        // Validar talla seleccionada
        if (chipGroupTalla.checkedChipId == View.NO_ID) {
            Snackbar.make(cardView, "Por favor selecciona una talla", Snackbar.LENGTH_SHORT).show()
            esValido = false
        }

        // Validar participación seleccionada
        if (chipGroupParticipacion.checkedChipId == View.NO_ID) {
            Snackbar.make(cardView, "Por favor selecciona el tipo de participación", Snackbar.LENGTH_SHORT).show()
            esValido = false
        }

        return esValido
    }

    private fun registrarParticipante() {
        val tallaChip = findViewById<Chip>(chipGroupTalla.checkedChipId)
        val participacionChip = findViewById<Chip>(chipGroupParticipacion.checkedChipId)

        val participante = Participante(
            nombre = etNombre.text.toString().trim(),
            apellidos = etApellidos.text.toString().trim(),
            codigo = etCodigo.text.toString().trim(),
            email = etEmail.text.toString().trim(),
            telefono = etTelefono.text.toString().trim(),
            tipo = spinnerTipo.selectedItem.toString(),
            facultad = spinnerFacultad.selectedItem.toString(),
            escuela = spinnerEscuela.selectedItem.toString(),
            talla = tallaChip.text.toString(),
            participacion = participacionChip.text.toString(),
            fechaRegistro = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        )

        participantes.add(participante)

        Snackbar.make(
            cardView,
            "¡Registro exitoso! Bienvenido al San Pedrito UNS 2025",
            Snackbar.LENGTH_LONG
        ).setAction("Ver Lista") {
            mostrarListaParticipantes()
        }.show()

        limpiarFormulario()
    }

    private fun limpiarFormulario() {
        etNombre.text?.clear()
        etApellidos.text?.clear()
        etCodigo.text?.clear()
        etEmail.text?.clear()
        etTelefono.text?.clear()
        spinnerTipo.setSelection(0)
        spinnerFacultad.setSelection(0)
        spinnerEscuela.setSelection(0)
        chipGroupTalla.clearCheck()
        chipGroupParticipacion.clearCheck()

        // Limpiar errores
        tilNombre.error = null
        tilApellidos.error = null
        tilCodigo.error = null
        tilEmail.error = null
        tilTelefono.error = null

        etNombre.requestFocus()
    }

    private fun mostrarListaParticipantes() {
        if (participantes.isEmpty()) {
            Snackbar.make(cardView, "No hay participantes registrados", Snackbar.LENGTH_SHORT).show()
            return
        }

        val lista = participantes.joinToString("\n\n") { participante ->
            "${participante.nombre} ${participante.apellidos}\n" +
                    "Código: ${participante.codigo}\n" +
                    "Tipo: ${participante.tipo}\n" +
                    "Facultad: ${participante.facultad}\n" +
                    "Escuela: ${participante.escuela}\n" +
                    "Talla: ${participante.talla}\n" +
                    "Participación: ${participante.participacion}\n" +
                    "Registro: ${participante.fechaRegistro}"
        }

        android.app.AlertDialog.Builder(this)
            .setTitle("Participantes Registrados (${participantes.size})")
            .setMessage(lista)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun mostrarEstadisticas() {
        if (participantes.isEmpty()) {
            Snackbar.make(cardView, "No hay datos para mostrar estadísticas", Snackbar.LENGTH_SHORT).show()
            return
        }

        val totalParticipantes = participantes.size
        val docentes = participantes.count { it.tipo == "Docente" }
        val alumnos = participantes.count { it.tipo == "Alumno" }
        val desfile = participantes.count { it.participacion.contains("Desfile") }
        val organizacion = participantes.count { it.participacion.contains("Organización") }

        val estadisticas = """
            📊 ESTADÍSTICAS SAN PEDRITO 2025
            
            Total de Participantes: $totalParticipantes
            
            Por Tipo:
            • Docentes: $docentes
            • Alumnos: $alumnos
            
            Por Participación:
            • Desfile: $desfile
            • Organización: $organizacion
            
            Facultades más representadas:
            ${participantes.groupBy { it.facultad }.entries
            .sortedByDescending { it.value.size }
            .take(3)
            .joinToString("\n") { "• ${it.key}: ${it.value.size}" }}
        """.trimIndent()

        android.app.AlertDialog.Builder(this)
            .setTitle("Estadísticas")
            .setMessage(estadisticas)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun exportarDatos() {
        if (participantes.isEmpty()) {
            Snackbar.make(cardView, "No hay datos para exportar", Snackbar.LENGTH_SHORT).show()
            return
        }

        Snackbar.make(cardView, "Funcionalidad de exportación próximamente", Snackbar.LENGTH_SHORT).show()
    }

    private fun abrirConfiguracion() {
        Snackbar.make(cardView, "Configuración próximamente", Snackbar.LENGTH_SHORT).show()
    }

    private fun mostrarAcercaDe() {
        val mensaje = """
            🎉 San Pedrito UNS 2025
            Universidad Nacional del Santa
            
            Aplicación desarrollada para el registro de participantes en el tradicional desfile de San Pedrito.
            
            Fecha del evento: 26 de Junio de 2025
            
            Desarrollado por: Estudiantes de Sistemas UNS
            Versión: 1.0.0
        """.trimIndent()

        android.app.AlertDialog.Builder(this)
            .setTitle("Acerca de")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun mostrarInfoEvento() {
        val mensaje = """
            🎊 DESFILE DE SAN PEDRITO 2025
            
            📅 Fecha: Jueves, 26 de Junio de 2025
            🕒 Hora: 9:00 AM
            📍 Lugar: Av. Pacífico - Centro de Chimbote
            
            El desfile de San Pedrito es una tradición universitaria que celebra al patrón de los pescadores, siendo una de las festividades más importantes de nuestra región.
            
            ¡Participa y vive la tradición UNS!
        """.trimIndent()

        android.app.AlertDialog.Builder(this)
            .setTitle("Información del Evento")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun compartirApp() {
        val intent = android.content.Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_TEXT,
            "¡Regístrate para el San Pedrito UNS 2025! 🎉\n" +
                    "Fecha: 26 de Junio de 2025\n" +
                    "Descarga la app y únete a la tradición universitaria.")
        startActivity(android.content.Intent.createChooser(intent, "Compartir aplicación"))
    }

    private fun mostrarContacto() {
        val mensaje = """
            📞 CONTACTO ORGANIZADORES
            
            📧 Email: sanpedrito@uns.edu.pe
            📱 WhatsApp: +51 999 888 777
            🏢 Oficina: Rectorado UNS
            
            Horario de atención:
            Lunes a Viernes: 8:00 AM - 5:00 PM
        """.trimIndent()

        android.app.AlertDialog.Builder(this)
            .setTitle("Contacto")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun abrirPaginaWeb() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/sanpedritochimbote/?locale=es_LA"))
        startActivity(intent)
    }
}

data class Participante(
    val nombre: String,
    val apellidos: String,
    val codigo: String,
    val email: String,
    val telefono: String,
    val tipo: String,
    val facultad: String,
    val escuela: String,
    val talla: String,
    val participacion: String,
    val fechaRegistro: String
)