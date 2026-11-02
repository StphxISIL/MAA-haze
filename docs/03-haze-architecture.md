---
title: haze-architecture
updated: 2026-11-02
status: draft
---

# Cómo está construida Haze

Para quien tenga que cambiar el código. Un solo módulo, `app`, con el paquete `com.stphxlb.isil.maa.project_03`. El nombre del paquete viene del proyecto de clase del que salió y no se cambió para no romper la instalación.

## Los paquetes

| Paquete o archivo | Qué hay |
|---|---|
| `MainActivity`, `DashboardActivity`, `ProductDetailActivity`, `OrderActivity` | Las 4 pantallas. Cada Activity arma su pantalla con `setContent` y le pasa las funciones que abren otras pantallas. |
| `components/` | Los componentes reutilizables. Ver [Los componentes](02-haze-components.md). |
| `Navigation.kt` | Qué Activity abre cada destino y la función `navigateTo`. |
| `Product.kt` | El modelo `Product`, la lectura del JSON y la llamada al servicio web. |
| `Cart.kt` | El carrito en memoria, el modelo `CartLine` y las funciones de precio. |
| `ui/theme/` | `Color.kt`, `Type.kt`, `Spacing.kt` y `Theme.kt`: el tema de toda la app. |
| `res/values/strings.xml` | Todo el texto que ve el usuario. Las pantallas no llevan frases escritas en el código. |

## Cada pantalla, en dos partes

Cada archivo de pantalla tiene:

1. **La Activity** | sabe de Android: lee el `Intent`, pide los datos y abre otras Activities.
2. **La función `@Composable` de la pantalla** (`MainScreen`, `HomeScreen`, `ProductDetailScreen`, `OrderScreen`) | solo dibuja. Recibe los datos y funciones como `onNavigate`; no sabe qué Activity hay detrás.

Así, la interfaz depende solo del estado que recibe, y los eventos suben hasta la Activity, que es la que actúa.

## El estado

| Estado | Dónde vive | Quién lo cambia |
|---|---|---|
| La carta (`products`) y el mensaje de carga (`status`) | `DashboardActivity`, con `mutableStateOf` | La llamada al servicio web, al abrir la carta |
| El texto del buscador y la categoría elegida | `HomeScreen`, con `remember` | El usuario |
| Tamaño y cantidad | `ProductDetailScreen`, con `remember` | El usuario |
| El carrito | `Cart`, un `object` con `mutableStateListOf` | Agregar al pedido y Confirmar |
| Delivery o Recojo, pedido hecho y aviso abierto | `OrderScreen`, con `remember` | El usuario |

`Cart.lines` es una lista que Compose observa: cuando se agrega o se vacía, se redibuja lo que la lee, como el globo del carrito o el total.

## La navegación

Las pantallas no llaman a `startActivity` para moverse por la barra. Llaman a `onNavigate(destino)`, y cada Activity lo conecta con `navigateTo(destino)` de `Navigation.kt`:

1. `HazeDestination.activityClass()` dice qué Activity corresponde a cada destino: `HOME` es la portada, `MENU` la carta y `ORDER` el pedido.
2. Si la pantalla actual ya es ese destino, no hace nada.
3. Si no, abre la Activity con `FLAG_ACTIVITY_REORDER_TO_FRONT`: si ya estaba abierta, la trae adelante con su estado, en vez de crear otra.

El detalle es la única pantalla que no está en la barra. Se abre con `ProductDetailActivity.open(activity, product)`, que pasa los datos del café en el `Intent`, y marca Carta en la barra porque se entra desde ahí.

## Los datos

- **El servicio web** | `https://stphxlb.alwaysdata.net/productos.php` devuelve un arreglo JSON con los cafés de la tabla.
- **La llamada** | `fetchProducts` abre un `Thread`, porque Android no deja usar la red en el hilo de la pantalla, y vuelve con `runOnUiThread`.
- **La lectura** | `parseProducts` convierte cada fila en un `Product`. El servicio manda los números como texto, por eso `idproducto` pasa por `toInt()` y `disponible` se compara con `"1"`.
- **Las fotos** | la tabla no trae fotos: todas las tarjetas usan `start_photo`.

## El tema

- **Colores** (`Color.kt`) | la paleta de Ember: `AppBackground`, `AppSurface`, `AppForeground`, `AppPrimary`, `AppSecondary`, `AppAccent` y `AppDeep`.
- **Fuente** (`Type.kt`) | DM Sans en tres pesos, desde `res/font/`.
- **Márgenes** (`Spacing.kt`) | `AppTheme.spacing.small`, `medium`, `large` y `extraLarge`, en vez de números sueltos en cada pantalla.
- **`Project_03_Theme`** (`Theme.kt`) | junta todo y envuelve cada pantalla. Los colores dinámicos del celular están apagados a propósito para que siempre se vea Ember.
- **`edgeToEdgeDark()`** | deja las barras del sistema oscuras con los iconos claros.
