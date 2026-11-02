---
title: versions-index
updated: 2026-11-02
status: draft
---

# Versiones

El registro de cambios del proyecto. Cada versión cuenta qué cambió **en palabras simples**, sin rutas de archivo ni jerga.

**El número de la versión actual no se repite aquí.** Está en [la portada del proyecto](../README.md) y arriba del todo en la lista de su PA. Un número copiado en tres lugares se queda viejo en uno de ellos.

## Por PA

- [PA3](lp3/00-lp3-index.md) | la PA actual. Prefijo `lp3`.

Cuando el proyecto siga en otra PA, se crea `lp4/` y el prefijo pasa a `lp4`, empezando en `lp4.0.0 | Base`. No se hace hasta que el propietario lo pida.

## Por qué `lp`

En el curso, cada evaluación del proyecto es un **Proceso de Aprendizaje**, o **PA**: PA1, PA2, PA3. `lp` es la sigla de su nombre en inglés, *learning process*, porque en este proyecto los nombres (carpetas, archivos, etiquetas) van en inglés. Así, `lp3` es la PA3, y el número dice en qué entrega del curso se hizo cada cambio.

## Cómo se lee un número

```
lp3 . 1 . 0
 │    │   └── Retoque  | documentación, un arreglo o una actualización de herramientas
 │    └────── Feature  | una funcionalidad nueva que se ve en la app
 └─────────── PA       | el proceso de aprendizaje en el que se hizo (lp3 = PA3)
```

Cada versión es un commit con el título `lp3.X.Y | Tipo` (`Base`, `Feature`, `Docs`, `Fix` o `Build`, con número romano cuando el tipo se repite en la PA: `Feature II`, `Docs II`) y su etiqueta de git con el mismo número.

## Las versiones y el registro de cambios de la app

Son dos registros distintos y no se mezclan:

- **Este** cuenta qué cambió en el repositorio: código, documentos, reglas y herramientas.
- **El [registro de cambios de Haze](../docs/05-haze-changelog.md)** cuenta cómo evolucionó la app, para quien la usa.

El protocolo completo, para agentes de IA, está en [Cómo versionar este proyecto](../rules/03-versioning.md).
