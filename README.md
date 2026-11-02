---
title: readme
updated: 2026-11-02
status: draft
---

# Haze

App Android de carta para una cafetería: se mira la carta, se elige un café con su tamaño y su cantidad, y se arma un pedido. Está hecha con Kotlin y Jetpack Compose para el curso Aplicaciones Móviles para Android de ISIL.

El nombre viene del vapor que sale de una taza recién servida.

Toda la documentación está en español. Los nombres de carpetas y archivos están en inglés, y el código sigue las convenciones de Kotlin.

Versión actual: **lp3.3.1** | ver el [registro de cambios](docs/05-haze-changelog.md).

## Por dónde empezar

1. [Cómo usar Haze](docs/01-haze-usage.md) | las pantallas, cómo se pasa de una a otra y cómo correrla.
2. [Los componentes](docs/02-haze-components.md) | qué hace cada pieza reutilizable y qué recibe.
3. [Cómo está construida](docs/03-haze-architecture.md) | los paquetes, el estado y la navegación, para quien tenga que cambiar el código.

Cada carpeta de documentación tiene su índice en `00-<carpeta>-index.md`. Todo está listado en el [índice de la documentación](docs/00-docs-index.md).

## Estructura

| Carpeta | Qué hay |
|---|---|
| `app/` | El proyecto Android: código Kotlin, recursos y fuentes. |
| `docs/` | La documentación: uso, componentes, arquitectura, problemas y cambios. |
| `gradle/` | El wrapper de Gradle y la lista de versiones de las librerías. |

## Referente visual

La interfaz sigue la plantilla [Ember Coffee Shop](https://www.behance.net/gallery/255454903/Ember-Coffee-Shop-Mobile-App-UXUI-Design) de Behance: fondo casi negro, botones pastilla en ámbar (`E8A15C`), ámbar claro (`F2BC84`), rojo ladrillo (`A03600`) y la fuente DM Sans.

## Qué hace hoy

- **La carta** | se lee de un servicio web PHP publicado en AlwaysData (`productos.php`), con buscador y filtro por categoría.
- **El detalle** | cada café con su tamaño (S, M o L), su cantidad y si está disponible.
- **El pedido** | Delivery o Recojo, las líneas, el total y un aviso para confirmar.
- **Las barras** | la misma barra arriba (con el carrito) y abajo (Inicio, Carta y Pedido) en las 4 pantallas, que quedan unidas entre sí.

## Con qué se construyó

| Pieza | Versión |
|---|---|
| Android | `minSdk` 31 (Android 12), `compileSdk` y `targetSdk` 37 |
| Kotlin | 2.2.10 |
| Compose | BOM 2026.02.01, Material 3 |
| Android Gradle Plugin | 9.3.2 |
| Probada en | Pixel 7 Pro físico |
