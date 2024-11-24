
# 🎵 Aplicación de Gestión de Álbumes

Esta aplicación te permite gestionar tus álbumes musicales favoritos. Podrás agregar, visualizar, actualizar y eliminar álbumes de forma sencilla, todo en una interfaz interactiva creada con Jetpack Compose. Además, incorpora funcionalidades interesantes como el uso de sensores.
github: https://github.com/gcorona72/TallerNew/
---

## 📋 Funcionalidades

### 🏠 Pantalla de Inicio
- Muestra un saludo dinámico dependiendo de la hora del día: *Buenos días*, *Buenas tardes* o *Buenas noches*.
- Incluye un botón para navegar hacia la pantalla principal de gestión de álbumes.

### 🎨 Gestión de Álbumes
1. **Lista de Álbumes:**
   - Visualiza todos los álbumes almacenados con su título y artista.
   - Haz clic en un álbum para ver más detalles.

2. **Detalles del Álbum:**
   - Muestra información completa de un álbum: título, artista y fecha de lanzamiento.

3. **Añadir Álbum:**
   - Diálogo para agregar un nuevo álbum proporcionando título, artista y fecha de lanzamiento.

4. **Editar y Eliminar Álbumes:**
   - Posibilidad de actualizar la descripción o eliminar álbumes según su título.

### 🎛️ Sensor de Movimiento
- **Funcionalidad Extra:**
  - Cambia el color de fondo al detectar movimientos bruscos, aprovechando el acelerómetro del dispositivo.

---

## 🛠️ Tecnologías Utilizadas
- **Kotlin**: Lenguaje principal para el desarrollo.
- **Jetpack Compose**: Framework moderno para interfaces de usuario.
- **ViewModel y StateFlow**: Gestión de estados y arquitectura reactiva.
- **Room Database**: Base de datos local para almacenar información de los álbumes.
- **Sensores Android**: Uso del acelerómetro para detección de movimientos.

---

## 🚀 Cómo Ejecutar el Proyecto

### Prerrequisitos
- Android Studio Arctic Fox o superior.
- Dispositivo físico o emulador con Android 8.0 (API 26) o superior.

### Pasos para Ejecutar
1. Clona el repositorio en tu máquina:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   ```
2. Abre el proyecto en Android Studio.
3. Sincroniza las dependencias (Gradle se encargará automáticamente).
4. Ejecuta la aplicación en un dispositivo físico o emulador.

---

## 📂 Estructura del Proyecto
```
com.example.tallernew
│
├── HomeScreen.kt       # Pantalla de bienvenida con saludo dinámico
├── MainActivity.kt     # Navegación entre pantallas
├── MainActivityScreen.kt  # Gestión de la lista de álbumes
├── AlbumViewModel.kt   # Lógica de negocio y acceso a la base de datos
├── Almacenamiento/
│   ├── Album.kt        # Modelo de datos para los álbumes
│   ├── AlbumDatabase.kt  # Base de datos local (Room)
├── SensorActivity.kt   # Actividad que usa el acelerómetro
```

---

## 🌟 Próximos Pasos
- Agregar soporte para imágenes de álbumes.
- Sincronización con servicios en la nube.
- Implementar búsqueda de álbumes en la lista.

---

## 🧑‍💻 Contribuciones
¡Contribuciones son bienvenidas! Por favor, abre un *Pull Request* o crea un *Issue* para cualquier sugerencia o mejora.

---

## 📞 Contacto
Si tienes preguntas o sugerencias, no dudes en contactarme.

---

¡Gracias por usar esta aplicación! 🎶
