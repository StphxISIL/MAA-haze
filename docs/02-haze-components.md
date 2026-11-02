---
title: haze-components
updated: 2026-11-02
status: draft
---

# Los componentes de Haze

Están en `app/src/main/java/com/stphxlb/isil/maa/project_03/components/`, un archivo por componente.

## La regla que siguen todos

Un componente **no guarda estado ni abre pantallas**. Recibe lo que tiene que mostrar por parámetro (`title`, `selected`, `cartCount`) y avisa lo que pasó con una función (`onClick`, `onNavigate`). La pantalla que lo usa tiene el estado y decide qué hacer.

Es el flujo unidireccional de datos de la capa de UI: el estado baja de la pantalla al componente y los eventos suben del componente a la pantalla. Por eso un mismo componente sirve en pantallas distintas: no sabe en cuál está.

## El marco de las pantallas

### `HazeScaffold`

El marco de las 4 Activities: `HazeTopBar` arriba, `HazeBottomBar` abajo y el contenido de la pantalla al medio. Es el componente que se repite en todas las pantallas; si cambia una barra, cambia en toda la app.

| Parámetro | Qué es |
|---|---|
| `title` | El texto de la barra de arriba. |
| `selected` | El botón de abajo que se pinta en ámbar (`HazeDestination`). |
| `cartCount` | El número del globo del carrito. |
| `onNavigate` | Se llama con el destino cuando se toca un botón de abajo o el carrito. |
| `onBackClick` | Opcional. Si llega, sale la flecha; si es `null`, sale el logo. |
| `content` | La pantalla. Recibe el espacio que ocupan las barras para que nada quede tapado. |

Dónde se usa: portada, carta, detalle y pedido.

### `HazeTopBar`

A la izquierda, la flecha de volver o el logo de Haze. Al medio, el título. A la derecha, el carrito con un globo ámbar (`BadgedBox`) que muestra cuántos cafés hay y solo aparece si hay alguno.

Recibe `title`, `cartCount`, `onCartClick` y `onBackClick` (opcional).

### `HazeBottomBar` y `HazeBarItem`

Tres botones: Inicio, Carta y Pedido. `HazeDestination` es la lista de esos tres destinos (`HOME`, `MENU`, `ORDER`). `HazeBarItem` es un botón suelto: icono arriba, texto abajo, ámbar si está elegido y café claro si no.

Recibe `selected` y `onNavigate`.

## Las piezas de cada pantalla

| Componente | Qué es | Recibe | Dónde se usa |
|---|---|---|---|
| `EmberButton` | El botón pastilla ámbar de la plantilla. Apagado, se ve del color de las tarjetas. | `text`, `onClick`, `modifier`, `enabled` | Empezar, Agregar al pedido, Hacer pedido y Confirmar |
| `ChoiceChip` | Una opción elegible: ámbar si está elegida, del color de las tarjetas si no. | `label`, `selected`, `onClick` | Categorías, tamaños, Delivery y Recojo |
| `FeaturedDrink` | La tarjeta grande: foto con un velo oscuro, categoría, nombre y precio encima. | `product`, `onClick` | La carta, primer café |
| `DrinkCard` | La tarjeta chica: foto arriba, nombre y precio abajo. | `product`, `onClick` | La carta, fila de los demás |
| `HazeDialog` | El aviso de la app, con fondo de tarjeta, bordes de 24 dp y Confirmar como `EmberButton`. | `title`, `message`, `confirmText`, `dismissText`, `onConfirm`, `onDismiss` | Hacer pedido |

## Por qué así

- **Un archivo por componente**, como en la demo de componentes del curso: se encuentra cada uno por su nombre.
- **Colores del tema, no sueltos** | los componentes toman los colores de `ui/theme/Color.kt`. Cambiar un color ahí lo cambia en todos.
- **Las tarjetas salieron de la carta** | antes estaban escritas dentro de `DashboardActivity`. Ahora reciben un `Product` y se pueden usar en cualquier pantalla.
