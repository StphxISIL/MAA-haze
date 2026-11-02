---
title: how-to-write-here
updated: 2026-11-02
status: draft
---

# Cómo escribir aquí

Las mismas convenciones que los proyectos propios del propietario, recortadas a lo que necesita este proyecto.

## Idioma

- **Contenido en español.** Documentación, textos de la app (`strings.xml`), comentarios del código y mensajes de commit.
- **Nombres en inglés.** Carpetas, archivos, identificadores del código, ramas y etiquetas.
- **Dentro de `app/` manda Kotlin.** Clases, paquetes y funciones siguen las convenciones de Kotlin y Android. El paquete `com.stphxlb.isil.maa.project_03` no se renombra.

## Cabecera de cada `.md`

Todo `.md` empieza así:

```
---
title: <identificador>
updated: <AAAA-MM-DD>
status: pending | draft | current
---
```

Después va un `# Título` en español y el contenido.

**`title` es el nombre del archivo sin el prefijo numérico y sin la extensión**: `01-haze-usage.md` tiene `title: haze-usage`. Si se renombra un archivo, su `title` cambia en el mismo paso.

**`status`** dice si el contenido es fiable:

| Valor | Significa |
|---|---|
| `pending` | Creado, sin contenido real todavía. |
| `draft` | Con contenido, sin revisar ni confirmar. |
| `current` | Confirmado por el propietario; se puede usar con confianza. |

La excepción es `CLAUDE.md`, que solo importa `AGENTS.md` para que Claude Code lo lea solo.

## Nombres únicos e índices

Cada carpeta tiene su índice en `00-<carpeta>-index.md`. Los archivos llevan un prefijo con su tema (`haze-usage`, no `usage`) para que ningún nombre se repita en el proyecto. Las carpetas de cada versión (`lp3.3.4/`) no llevan índice: tienen dos archivos y no hace falta.

## Un párrafo por línea

Cada párrafo va en una sola línea, por largo que sea. Solo se corta entre puntos de una lista, entre filas de una tabla o dentro de un bloque de código.

## Nada de raya larga

La raya larga (U+2014) no se usa en ningún archivo. En su lugar va la barra vertical ` | `. Dentro de una tabla, donde `|` ya separa columnas, se reescribe la frase con dos puntos o una coma.

## Comandos en su propio bloque

Cada comando que el propietario pueda ejecutar va en su propio bloque `bash`, para copiarlo solo.
