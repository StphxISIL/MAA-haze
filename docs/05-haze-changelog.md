---
title: haze-changelog
updated: 2026-11-02
status: draft
---

# Registro de cambios de Haze

Cómo llegó a ser lo que es. La numeración está en [Versiones](../versions/00-versions-index.md).

## lp3.3.6 | Fix | 2026-11-02

**Atrás desde el pedido vuelve a la carta.** Agregar al pedido cierra el detalle, así que el botón atrás ya no pasa por los cafés que se vieron antes.

**Textos más claros.** El saludo lleva su signo de apertura y, sin internet, la carta pide revisar la conexión.

**Menos peso.** Salen imágenes y fuentes que no se usaban y no eran de la cafetería.

## lp3.3.4 | Docs II | 2026-11-02

**Reglas de trabajo y versiones.** El proyecto explica cómo se escribe, qué nunca se hace, cómo se numera cada versión y cómo se hace un commit, para que cualquier agente de IA lo siga igual. La app no cambia.

## lp3.3.3 | Build | 2026-11-02

**Herramientas de compilación al día.** El plugin de Android pasa de 9.3.2 a 9.4.1 y Gradle de 9.5.0 a 9.6.0. Lo propuso Android Studio al abrir el proyecto; la app no cambia y se probó en el celular con estas versiones.

## lp3.3.2 | Fix | 2026-11-02

**Arreglado: Android Studio marcaba en rojo una carpeta de git que no existe.** El proyecto traía en su configuración una segunda carpeta de git, heredada de cuando estaba en otra ubicación. Se quitó; la app no cambia.

**De dónde salió:** al abrir el proyecto en Android Studio, *Directory Mappings* mostraba esa carpeta en rojo.

## lp3.3.1 | Docs | 2026-11-02

**La documentación.** Un README como portada y la carpeta `docs/` con cómo usarla, los componentes, cómo está construida, qué hacer cuando falla y este registro.

**De dónde salió:** hacía falta poder explicar cada componente y la navegación sin abrir el código.

## lp3.3.0 | Feature III | 2026-11-02

**Las tarjetas de café son componentes.** `FeaturedDrink` y `DrinkCard` salieron de la carta a `components/`. Reciben un café y avisan cuando se tocan; ya no dependen de la pantalla en la que están.

**Hacer pedido pregunta antes.** Antes vaciaba el carrito al instante. Ahora sale `HazeDialog`, un aviso con los colores de Ember y el total del pedido: Confirmar lo hace, Seguir mirando lo cierra sin tocar nada.

**Más aire en la carta.** Un margen arriba y abajo de la lista, para que no quede pegada a las barras.

## lp3.2.0 | Feature II | 2026-11-02

**Las 4 pantallas quedan unidas.** `HazeScaffold` junta las dos barras y se usa en la portada, la carta, el detalle y el pedido. Antes la barra de abajo solo estaba en la carta, con dos botones, y el resto de pantallas solo tenía una flecha para volver: se avanzaba en línea recta y no se podía, por ejemplo, ir del pedido a la portada.

**Una sola forma de moverse.** `navigateTo` abre cada destino y, si la pantalla ya estaba abierta, la trae adelante en vez de crear otra. La carta no vuelve a pedir el JSON.

**El globo del carrito.** La barra de arriba muestra cuántos cafés hay en el pedido.

**Arreglado: el pedido se quedaba en "Listo".** Si se hacía un pedido y después se agregaba otro café, la pantalla seguía mostrando el mensaje anterior. Ahora muestra el pedido nuevo.

## lp3.1.0 | Feature | 2026-11-02

**Nace `components/`.** `HazeTopBar` (título, flecha o logo, y carrito) y `HazeBottomBar` (Inicio, Carta y Pedido), listos para usarse en cualquier pantalla. `EmberButton` y `ChoiceChip`, que estaban juntos en un solo archivo, pasan a un archivo cada uno.

## lp3.0.0 | Base | 2026-11-02

**Haze tal como se entregó en la PA1.** Portada, carta leída del servicio web PHP, detalle de cada café y pedido, con el tema de la plantilla Ember.
