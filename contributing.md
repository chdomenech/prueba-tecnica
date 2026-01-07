# Guía de Contribución

## Flujo de Trabajo

Para contribuir, necesitas:

1. **Crear un fork** del repositorio
2. **Hacer tus cambios** en tu fork
3. **Crear un Pull Request** para que revisemos tu código

---

## Pasos para Contribuir

### Paso 1: Crear un Fork

1. Ve a la página principal del repositorio en GitHub
2. Haz clic en el botón **Fork** (arriba a la derecha)
3. Espera a que se cree tu fork

Ahora tendrás tu propia copia en `https://github.com/TU-USUARIO/NOMBRE-DEL-REPO`

### Paso 2: Clonar Tu Fork

```bash
git clone https://github.com/TU-USUARIO/NOMBRE-DEL-REPO.git
cd NOMBRE-DEL-REPO
```

**Reemplaza:**
- `TU-USUARIO` con tu nombre de usuario de GitHub
- `NOMBRE-DEL-REPO` con el nombre del repositorio

### Paso 3: Configurar Autenticación

Para poder hacer push a tu fork, necesitas autenticarte. GitHub ya no acepta contraseñas, así que necesitas crear un **Personal Access Token (PAT)**:

1. Ve a GitHub → Tu perfil → **Settings** → **Developer settings** → **Personal access tokens** → **Tokens (classic)**
2. Haz clic en **Generate new token (classic)**
3. Dale un nombre descriptivo (ej: "contributions-token")
4. Selecciona el scope: ✅ **`repo`**
5. Haz clic en **Generate token**
6. **⚠️ IMPORTANTE:** Copia el token inmediatamente (no lo volverás a ver)

Cuando Git te pida contraseña:
- **Usuario:** Tu nombre de usuario de GitHub
- **Contraseña:** Pega tu Personal Access Token (no tu contraseña de GitHub)

### Paso 4: Sincronizar con el Repositorio Principal

Agrega el repositorio original como "upstream" y sincroniza:

```bash
# Agregar upstream (reemplaza con la URL del repositorio original)
git remote add upstream https://github.com/ORGANIZACION/NOMBRE-DEL-REPO.git

# Obtener los últimos cambios
git fetch upstream
git checkout master
git merge upstream/master
git push origin master
```

**Nota:** Si la rama principal se llama `main` en lugar de `master`, usa `main` en los comandos.

### Paso 5: Crear una Rama para Tus Cambios

Crea una rama con un nombre descriptivo:

```bash
git checkout -b nombre-descriptivo-de-tu-cambio
```

Ejemplo:
```bash
git checkout -b fix-authentication-bug
```

### Paso 6: Hacer Tus Cambios

- Realiza tus cambios de código/configuración
- Prueba tus cambios
- Sigue las guías del proyecto en [README.md](README.md) si aplica

### Paso 7: Hacer Commit y Push

```bash
# Agregar cambios
git add .

# Hacer commit
git commit -m "Descripción clara de tus cambios"

# Hacer push a tu fork
git push origin nombre-de-tu-rama
```

**Mejor Práctica:** Haz varios commits pequeños en lugar de un commit grande.

### Paso 8: Crear un Pull Request

1. Ve a tu fork en GitHub: `https://github.com/TU-USUARIO/NOMBRE-DEL-REPO`
2. Verás un banner con el botón **Compare & pull request**
3. Haz clic en **Compare & pull request**
4. Completa el formulario:
   - **Título:** Describe brevemente tus cambios
   - **Descripción:** Explica qué cambiaste y por qué
   - **Base repository:** Debe ser el repositorio original
   - **Base branch:** Generalmente `master` o `main`
5. Haz clic en **Create pull request**

---

## Mantener Tu Fork Actualizado

Si necesitas actualizar tu fork con los últimos cambios del repositorio principal:

```bash
git fetch upstream
git checkout master  # o 'main' según corresponda
git merge upstream/master  # o 'upstream/main'
git push origin master  # o 'main'
```

Si estás trabajando en una rama y quieres actualizarla:

```bash
git checkout tu-rama
git fetch upstream
git merge upstream/master  # o 'upstream/main'
git push origin tu-rama
```

---

## ¿Necesitas Ayuda?

- Documentación de GitHub sobre forks: https://docs.github.com/en/get-started/quickstart/fork-a-repo
- Guía de pull requests: https://docs.github.com/en/pull-requests
- Contacta a los mantenedores del repositorio

---

**Recuerda:** 
- Trabaja siempre en tu fork, no en el repositorio original
- Mantén tu fork sincronizado antes de crear nuevas ramas
