---
title: readme
updated: 2026-11-02
status: draft
---

# Haze

App Android de carta para una cafetería: se mira la carta, se elige un café con su tamaño y su cantidad, y se arma un pedido. Está hecha con Kotlin y Jetpack Compose para el curso Aplicaciones Móviles para Android de ISIL.

El nombre viene del vapor que sale de una taza recién servida.

Toda la documentación está en español. Los nombres de carpetas y archivos están en inglés, y el código sigue las convenciones de Kotlin.

Versión actual: **lp3.4.1** | ver [Versiones](versions/00-versions-index.md) y el [registro de cambios](docs/05-haze-changelog.md). `lp3` quiere decir PA3, el Proceso de Aprendizaje 3 del curso (*learning process*, en inglés).

## Por dónde empezar

1. [Cómo usar Haze](docs/01-haze-usage.md) | las pantallas, cómo se pasa de una a otra y cómo correrla.
2. [Los componentes](docs/02-haze-components.md) | qué hace cada pieza reutilizable y qué recibe.
3. [Cómo está construida](docs/03-haze-architecture.md) | los paquetes, el estado y la navegación, para quien tenga que cambiar el código.

Si eres un agente de IA, empieza por `AGENTS.md` y por las [reglas de trabajo](rules/00-rules-index.md) antes de tocar nada.

Cada carpeta tiene su índice en `00-<carpeta>-index.md`. La documentación está listada en el [índice de la documentación](docs/00-docs-index.md).

## Estructura

| Carpeta | Qué hay |
|---|---|
| `app/` | El proyecto Android: código Kotlin, recursos y fuentes. |
| `docs/` | La documentación: uso, componentes, arquitectura, problemas y cambios. |
| `rules/` | Las reglas de trabajo para agentes de IA: cómo escribir, qué nunca hacer, cómo versionar y git. |
| `versions/` | El registro de versiones, una carpeta por versión. |
| `gradle/` | El wrapper de Gradle y la lista de versiones de las librerías. |

## Referente visual

La interfaz sigue la plantilla [Ember Coffee Shop](https://www.behance.net/gallery/255454903/Ember-Coffee-Shop-Mobile-App-UXUI-Design) de Behance: fondo casi negro, botones pastilla con degradado ámbar (`E8A15C` a `F2BC84`), ámbar claro (`F2BC84`), rojo ladrillo (`A03600`) y la fuente DM Sans.

## Fotos

Las fotos de cada categoría son de [Unsplash](https://unsplash.com) y se usan bajo su licencia, que permite usarlas gratis sin pedir permiso. Se guardaron como `.webp` de 800 px.

| Categoría | Foto |
|---|---|
| Espresso | [unsplash.com/photos/6cY1xBGn9ZI](https://unsplash.com/photos/white-ceramic-coffee-mug-with-capuccino-6cY1xBGn9ZI) |
| Filtro | [unsplash.com/photos/KGR2u2rG6c4](https://unsplash.com/photos/a-coffee-maker-pouring-coffee-into-a-cup-KGR2u2rG6c4) |
| Frío | [unsplash.com/photos/rzX8bOle9xs](https://unsplash.com/photos/a-glass-filled-with-liquid-and-ice-on-top-of-a-table-rzX8bOle9xs) |
| Té | [unsplash.com/photos/NOU_wT-jJpM](https://unsplash.com/photos/a-glass-cup-filled-with-liquid-sitting-on-top-of-a-saucer-NOU_wT-jJpM) |
| Pastelería | [unsplash.com/photos/m9pzwmxm2rk](https://unsplash.com/photos/two-brown-croissants-m9pzwmxm2rk) |

## Qué hace hoy

- **La carta** | se lee de un servicio web PHP publicado en AlwaysData (`productos.php`), con buscador y filtro por categoría.
- **El detalle** | cada café con tarjetas de tamaño (S, M o L con sus ml), su cantidad y si está disponible.
- **El pedido** | Delivery o Recojo, las líneas con - y + y una X para quitarlas, el total y un aviso para confirmar.
- **Las barras** | la misma barra arriba (con el carrito) y abajo (Inicio, Carta en el botón central y Pedido) en las 4 pantallas, que quedan unidas entre sí.

## Con qué se construyó

| Pieza | Versión |
|---|---|
| Android | `minSdk` 31 (Android 12), `compileSdk` y `targetSdk` 37 |
| Kotlin | 2.2.10 |
| Compose | BOM 2026.02.01, Material 3 |
| Android Gradle Plugin | 9.4.1 |
| Gradle | 9.6.0 |
| Probada en | Pixel 7 Pro físico |
