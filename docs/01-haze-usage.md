---
title: haze-usage
updated: 2026-11-02
status: draft
---

# Cómo usar Haze

## Las pantallas

| Pantalla | Qué se hace ahí | En la barra de abajo |
|---|---|---|
| **Portada** (`MainActivity`) | Es la que abre el icono. Frase, logo, **Empezar** y **Términos**, que despliega el texto en la misma pantalla. | Inicio |
| **Carta** (`DashboardActivity`) | Saludo, buscador, chips de categoría, una tarjeta grande con el primer café y una fila con los demás. | Carta |
| **Detalle** (`ProductDetailActivity`) | Un café: precio, categoría, descripción, si está disponible, tamaño, cantidad y **Agregar al pedido**. | Carta |
| **Pedido** (`OrderActivity`) | Delivery o Recojo, las líneas del pedido, el total y **Hacer pedido**. | Pedido |

## Cómo se pasa de una a otra

Las 4 pantallas tienen la misma barra arriba y la misma abajo, así que desde cualquiera se llega a las otras.

- **La barra de abajo** | Inicio abre la portada, Carta abre la carta y Pedido abre el pedido. El botón de la pantalla actual se ve en ámbar.
- **El carrito de arriba** | abre el pedido. Encima lleva un globo con la cantidad de cafés, que solo aparece si hay algo.
- **La flecha de arriba** | solo en el detalle. Vuelve a la carta.
- **Empezar** | en la portada, abre la carta.
- **Tocar un café** | en la carta, abre su detalle.
- **Agregar al pedido** | en el detalle, guarda el café, abre el pedido y cierra el detalle, así que atrás desde el pedido vuelve a la carta. Si el café no está disponible, el botón dice **Agotado** y no responde.

Si una pantalla ya estaba abierta, se trae adelante en vez de abrir otra copia: la carta no vuelve a cargar y el botón atrás del celular no se llena de pantallas repetidas.

## Hacer un pedido

1. En la carta, toca un café.
2. Elige el tamaño y la cantidad, y toca **Agregar al pedido**.
3. En el pedido, elige **Delivery** o **Recojo** y revisa el total.
4. Toca **Hacer pedido**. Sale un aviso con el total:
   - **Confirmar** vacía el carrito y deja el mensaje de pedido anotado;
   - **Seguir mirando**, o tocar fuera del aviso, lo cierra sin tocar el carrito.

El pedido no se envía a ningún servidor: vive en la memoria de la app mientras está abierta.

## Correrla

En Android Studio, abre la carpeta del proyecto, elige el dispositivo arriba y pulsa **Run** (Shift+F10).

- **En un celular** | activa *Opciones de desarrollador › Depuración USB*, conéctalo por cable y acepta el permiso. Hace falta Android 12 o más.
- **En un emulador** | un dispositivo con API 31 o más (Android 12); API 35 con *Google APIs* va más liviano. Si va lento, ver [Qué hacer cuando falla](04-haze-troubleshooting.md).

Para compilar sin Android Studio:

```bash
./gradlew assembleDebug
```

El APK queda en `app/build/outputs/apk/debug/app-debug.apk`.
