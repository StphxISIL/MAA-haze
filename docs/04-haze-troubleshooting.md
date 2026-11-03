---
title: haze-troubleshooting
updated: 2026-11-02
status: draft
---

# Qué hacer cuando Haze falla

Ordenado de lo más probable a lo más raro.

## La carta dice "No se pudo cargar la carta"

La app no pudo leer el servicio web. Revisa en este orden:

1. **Internet** | el celular o el emulador tiene que estar conectado.
2. **El servicio** | abre `https://stphxlb.alwaysdata.net/productos.php` en el navegador. Tiene que salir un arreglo JSON.
3. **El permiso** | `AndroidManifest.xml` tiene que llevar `android.permission.INTERNET`.

Si el navegador muestra el JSON y la app no, el detalle del error queda en Logcat con la etiqueta `Haze`.

## La carta dice "La carta llegó vacía"

El servicio respondió bien, pero con `[]`: la tabla no tiene filas. No es un error de la app.

## El emulador va muy lento

Casi siempre es falta de RAM. El emulador ocupa varios GB y, con Android Studio abierto, el sistema empieza a usar el disco como memoria.

- Mejor probar en un celular por USB.
- Abrir el emulador en su propia ventana: desmarcar *Settings › Tools › Emulator › Launch in the Running Devices tool window*.
- Usar una imagen más liviana, API 35 con *Google APIs* en vez de *Google Play*, con 2 GB de RAM.
- Cerrar lo que no se use y, después de compilar, `./gradlew --stop`.

## `./gradlew` dice "Permission denied"

Al archivo le falta el permiso de ejecución:

```bash
chmod +x gradlew
```

## Gradle no encuentra el SDK

Falta `local.properties`, que no se sube a git porque cada PC tiene su ruta. Android Studio lo crea al abrir el proyecto. A mano:

```bash
echo "sdk.dir=$HOME/Android/Sdk" > local.properties
```

Además, en el *SDK Manager* tiene que estar instalada la plataforma **Android API 37**.

## Android Studio marca en rojo una carpeta en *Directory Mappings*

`.idea/vcs.xml` apunta a una carpeta de git que ya no existe. Se quita la fila en *Settings › Version Control › Directory Mappings*. Pasó una vez, al copiar el proyecto de carpeta, y se arregló en lp3.3.2.
