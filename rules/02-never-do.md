---
title: never-do
updated: 2026-11-02
status: draft
---

# Qué nunca hacer

1. **Nunca hacer `push` sin permiso.** El commit se hace cuando el propietario lo pide; el push lo autoriza cada vez. Ver [Git](04-git.md).
2. **Nunca firmarse en un commit.** Ni `Co-Authored-By`, ni "Generated with", ni nada parecido. El historial se evalúa y el único autor es el propietario. Ver [Git](04-git.md).
3. **Nunca reescribir commits que ya están en GitHub.** Reescribir lo publicado obliga a un push forzado, y eso solo lo pide el propietario para un caso concreto.
4. **Nunca hacer un commit sin compilar.** Antes de cada commit, `./gradlew assembleDebug` tiene que terminar bien.
5. **Nunca subir lo que es de cada PC.** `local.properties`, `build/`, `.gradle/` ni claves. El `.gitignore` ya los deja fuera; no se fuerzan con `git add -f`.
6. **Nunca renombrar el paquete ni las Activities.** El nombre del paquete fija cómo se instala la app; cambiarlo rompe la instalación en los celulares donde ya está.
7. **Nunca inventar datos.** Ni del curso, ni del docente, ni de fechas o notas. Si falta un dato, se marca como pendiente y se pregunta.
8. **Nunca borrar documentación sin preguntar.** Se propone; el propietario confirma.
9. **Nunca usar la raya larga (U+2014).** Ver [Cómo escribir aquí](01-how-to-write-here.md).
