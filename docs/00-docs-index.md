---
title: docs-index
updated: 2026-11-02
status: draft
---

# Documentación de Haze

Todo lo que hace falta para usar, entender y cambiar la app.

## Los archivos

- [Cómo usar Haze](01-haze-usage.md) | las pantallas, la navegación y cómo correrla en un celular o un emulador.
- [Los componentes](02-haze-components.md) | cada componente reutilizable, qué recibe y dónde se usa.
- [Cómo está construida](03-haze-architecture.md) | paquetes, estado, navegación, datos y tema.
- [Qué hacer cuando falla](04-haze-troubleshooting.md) | de lo más probable a lo más raro.
- [Registro de cambios](05-haze-changelog.md) | cómo llegó a ser lo que es.

## Cómo se numera

```
lp3 . 1 . 0
 │    │   └── Retoque  | documentación o un arreglo pequeño.
 │    └────── Feature  | una funcionalidad nueva.
 └─────────── PA3      | el proceso de aprendizaje en el que se hizo.
```

Manda el nivel más alto que se tocó, y lo que queda a su derecha vuelve a 0. Cada versión es un commit con el título `lp3.X.Y | Qué es` y su etiqueta de git con el mismo número.
