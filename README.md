🎉 San Pedrito UNS 2025 - App de Registro
Descripción del Proyecto
San Pedrito UNS 2025** es una aplicación Android nativa desarrollada para gestionar el registro de participantes del evento tradicional "San Pedrito" de la Universidad Nacional del Santa. La aplicación permite registrar estudiantes, docentes y personal administrativo para el evento del 26 de junio de 2025, recopilando información personal, académica y preferencias del evento.

🛠️ Componentes Android Utilizados
1. ScrollView con LinearLayout
- Propósito: Contenedor principal que permite desplazamiento vertical
- Implementación: Envuelve toda la interfaz para manejar formularios largos
  
2. MaterialCardView
- Propósito: Contenedores con diseño Material Design
- Implementación: Dos tarjetas principales (header y formulario)

3. TextInputLayout con TextInputEditText
- Propósito: Campos de entrada de texto con validación y estilo Material
- Implementación: 6 campos (Nombres, Apellidos, Código, Email, Teléfono)
  
4. Spinner (Dropdown)
- Propósito: Listas desplegables para selección única
- Implementación: 3 spinners (Tipo de Participante, Facultad, Escuela)

5. ChipGroup con Chips
- Propósito: Selección de opciones mediante chips interactivos
- Implementación: 2 grupos (Talla de Polo, Tipo de Participación)
  
📱 Tipos de Menú Implementados
1) Options Menu (main_menu.xml)
Menú principal de la aplicación con las siguientes opciones:
- 📋 Lista de Participantes: Visualización de registros
- 📊 Estadísticas: Análisis de datos del evento
- 🌐 Página Web San Pedrito: Acceso directo al sitio web
- 💾 Exportar Datos: Función de respaldo
- ⚙️ Configuración: Ajustes de la aplicación
- ℹ️ Acerca de: Información de la app

2) Context Menu (context_menu.xml)
Menú contextual con opciones específicas:
- 📝 Información: Detalles adicionales
- 🔗 Compartir: Funcionalidad de compartir
- 📞 Contacto: Información de contacto

🌐 WebView Integration
WebView Activity (activity_webview.xml)
- Propósito: Navegación web integrada dentro de la aplicación
- Características:
  - ProgressBar horizontal: Indicador de carga de páginas web
  - Tinting personalizado: Barra de progreso en color institucional
  - Layout responsivo: WebView ocupa toda la pantalla disponible
- Funcionalidad: Permite acceder al sitio web oficial de San Pedrito sin salir de la aplicación

🎨 Diseño y UX
Características de Diseño:
- Paleta de colores: Basada en el rojo institucional de la UNS
- Tipografía: "sans-serif-light" para consistencia visual
- Material Design: Componentes modernos con elevaciones y sombras
- Responsive: Adaptable a diferentes tamaños de pantalla
- Accesibilidad: Hints claros y navegación intuitiva

Estructura Visual:
- Header informativo: Título del evento, fecha y propósito
- Secciones organizadas: Información personal, académica y del evento
- Botones de acción: Limpiar y Registrar con estilos diferenciados
- Footer institucional: Branding de la Universidad Nacional del Santa

🚀 Funcionalidades Principales
1. Registro completo de participantes
2. Validación de campos obligatorios
3. Selección de tallas y tipo de participación
4. Integración con sistemas web universitarios
5. Exportación de datos para administración
6. Interfaz intuitiva y accesible

📋 Campos de Registro
- Información Personal: Nombres, apellidos, código universitario, email, teléfono
- Información Académica: Facultad y escuela profesional
- Información del Evento: Talla de polo y tipo de participación

Capturas de la Aplicación
1) Formulario de la Aplicación:
![image](https://github.com/user-attachments/assets/28760d9e-770d-41cb-8525-f3d4d8fa0f95)
![image](https://github.com/user-attachments/assets/58e9c0e4-bfc7-47e3-80ab-88ddc601765a)

2) Menú de Opciones:
![image](https://github.com/user-attachments/assets/9797c994-ae74-4bb7-b46d-52c235da9f51)
![image](https://github.com/user-attachments/assets/0a753b63-6095-469a-b2e6-66af67b2760d)
![image](https://github.com/user-attachments/assets/6b5a2333-a89c-4300-b186-fc42f19e18be)
![image](https://github.com/user-attachments/assets/b9a0ebfe-d769-43b7-90e3-b6ffab13869f)
![image](https://github.com/user-attachments/assets/efbfc2cb-3356-4cea-b2e1-3584be8090c7)

3) Menú Contextual:
![image](https://github.com/user-attachments/assets/f894d452-97f0-4215-b10b-14e7ff6edb23)
![image](https://github.com/user-attachments/assets/4ba4ac43-b8ea-4ecb-ac10-a86c0ee6efa0)
![image](https://github.com/user-attachments/assets/a86ec94b-0a31-4794-b725-6eea34564243)
![image](https://github.com/user-attachments/assets/c41c3c49-7037-408d-8f13-05665fb9d773)


*Desarrollado para la Universidad Nacional del Santa de parte del estudiante Lujan Trujillo Anderson Junior- ¡Vive la tradición universitaria!* 🏛️
