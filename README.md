🎉 San Pedrito UNS 2025 - App de Registro
Descripción del Proyecto

**San Pedrito UNS 2025** es una aplicación Android nativa desarrollada para gestionar el registro de participantes del evento tradicional "San Pedrito" de la Universidad Nacional del Santa. La aplicación permite registrar estudiantes, docentes y personal administrativo para el evento del 26 de junio de 2025, recopilando información personal, académica y preferencias del evento.

## 🛠️ Componentes Android Utilizados

### 1. **ScrollView con LinearLayout**
- **Propósito**: Contenedor principal que permite desplazamiento vertical
- **Implementación**: Envuelve toda la interfaz para manejar formularios largos
- **Características**: 
  - `fillViewport="true"` para ocupar toda la pantalla
  - Fondo personalizado con color institucional (`@color/uns_red`)

### 2. **MaterialCardView**
- **Propósito**: Contenedores con diseño Material Design
- **Implementación**: Dos tarjetas principales (header y formulario)
- **Características**:
  - Bordes redondeados (`app:cardCornerRadius="12dp"`)
  - Elevación para efecto sombra (`app:cardElevation="8dp"`)
  - Fondo blanco contrastante con el fondo rojo

### 3. **TextInputLayout con TextInputEditText**
- **Propósito**: Campos de entrada de texto con validación y estilo Material
- **Implementación**: 6 campos (Nombres, Apellidos, Código, Email, Teléfono)
- **Características**:
  - Estilo outlined box para mejor visibilidad
  - Colores personalizados (`app:boxStrokeColor` y `app:hintTextColor`)
  - Tipos de entrada específicos (`textPersonName`, `textEmailAddress`, `phone`)

### 4. **Spinner (Dropdown)**
- **Propósito**: Listas desplegables para selección única
- **Implementación**: 3 spinners (Tipo de Participante, Facultad, Escuela)
- **Características**:
  - Fondo personalizado (`@drawable/spinner_background`)
  - Altura fija de 48dp siguiendo guidelines de Material Design

### 5. **ChipGroup con Chips**
- **Propósito**: Selección de opciones mediante chips interactivos
- **Implementación**: 2 grupos (Talla de Polo, Tipo de Participación)
- **Características**:
  - `app:singleSelection="true"` para selección única
  - Espaciado horizontal personalizado (`app:chipSpacingHorizontal="8dp"`)

## 📱 Tipos de Menú Implementados

### **Options Menu (main_menu.xml)**
Menú principal de la aplicación con las siguientes opciones:
- **📋 Lista de Participantes**: Visualización de registros
- **📊 Estadísticas**: Análisis de datos del evento
- **🌐 Página Web San Pedrito**: Acceso directo al sitio web
- **💾 Exportar Datos**: Función de respaldo
- **⚙️ Configuración**: Ajustes de la aplicación
- **ℹ️ Acerca de**: Información de la app

### **Context Menu (context_menu.xml)**
Menú contextual con opciones específicas:
- **📝 Información**: Detalles adicionales
- **🔗 Compartir**: Funcionalidad de compartir
- **📞 Contacto**: Información de contacto

## 🌐 WebView Integration

### **WebView Activity (activity_webview.xml)**
- **Propósito**: Navegación web integrada dentro de la aplicación
- **Características**:
  - **ProgressBar horizontal**: Indicador de carga de páginas web
  - **Tinting personalizado**: Barra de progreso en color institucional
  - **Layout responsivo**: WebView ocupa toda la pantalla disponible
- **Funcionalidad**: Permite acceder al sitio web oficial de San Pedrito sin salir de la aplicación

## 🎨 Diseño y UX

### Características de Diseño:
- **Paleta de colores**: Basada en el rojo institucional de la UNS
- **Tipografía**: `sans-serif-light` para consistencia visual
- **Material Design**: Componentes modernos con elevaciones y sombras
- **Responsive**: Adaptable a diferentes tamaños de pantalla
- **Accesibilidad**: Hints claros y navegación intuitiva

### Estructura Visual:
- **Header informativo**: Título del evento, fecha y propósito
- **Secciones organizadas**: Información personal, académica y del evento
- **Botones de acción**: Limpiar y Registrar con estilos diferenciados
- **Footer institucional**: Branding de la Universidad Nacional del Santa

## 🚀 Funcionalidades Principales

1. **Registro completo de participantes**
2. **Validación de campos obligatorios**
3. **Selección de tallas y tipo de participación**
4. **Integración con sistemas web universitarios**
5. **Exportación de datos para administración**
6. **Interfaz intuitiva y accesible**

## 📋 Campos de Registro

- **Información Personal**: Nombres, apellidos, código universitario, email, teléfono
- **Información Académica**: Facultad y escuela profesional
- **Información del Evento**: Talla de polo y tipo de participación

---

*Desarrollado para la Universidad Nacional del Santa - ¡Vive la tradición universitaria!* 🏛️
