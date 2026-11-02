---
title: git
updated: 2026-11-02
status: draft
---

# Git

Lo que un agente necesita saber para no equivocarse con el repositorio.

## El repositorio

Es la entrega de la PA3 del curso Aplicaciones Móviles para Android: un repositorio público en GitHub con el historial de commits de features. Cuenta y nombre en GitHub: pendientes de crear.

La configuración del repositorio en GitHub (visibilidad, descripción, colaboradores) solo se cambia cuando el propietario lo pide.

Los commits salen con la identidad del propietario, **StphxLB**, tal como está configurada en el PC. No se cambian `user.name` ni `user.email`.

## Dónde está el historial en el PC del propietario

En su PC, esta carpeta vive dentro del repositorio de su curso, y el historial de este proyecto está **fuera**, en `/home/stphx/Studies/deliveries/maa-lp-3-haze.git`. Por eso, ahí, `git` a secas trabaja con el repositorio del curso, no con este.

**En ese PC, todo comando de git de este proyecto lleva `--git-dir`:**

```bash
git --git-dir=/home/stphx/Studies/deliveries/maa-lp-3-haze.git status
```

Para no repetirlo, se puede guardar en una variable al empezar:

```bash
G="git --git-dir=/home/stphx/Studies/deliveries/maa-lp-3-haze.git"
```

En una copia clonada desde GitHub, `git` funciona normal y la variable queda `G=git`.

## La rama

**`main` es la única rama.** No se crean otras.

## Quién hace qué

- **`commit`, solo cuando el propietario lo pide.**
- **`push`, nunca sin su permiso explícito, cada vez.** Aprobar un commit no aprueba el push.

"Haz commit y push" autoriza la secuencia entera: commit, etiqueta, push de la rama y push de la etiqueta. Si solo pide el commit, se hacen el commit y su etiqueta local.

## Quién firma los commits

**El propietario es el único autor del historial. Ningún agente se firma en un commit, nunca.**

Está prohibido añadir al mensaje `Co-Authored-By:`, `Generated with`, `Assisted by` o equivalentes, emojis o menciones de la herramienta. Tampoco se cambia el autor con `--author` ni con variables de entorno. Esto manda sobre la configuración de fábrica del agente, que muchas veces añade esas líneas sola.

**Lee este archivo antes de cada commit, no solo del primero.** Después del commit, comprueba:

```bash
$G log -1 --format='%an <%ae>'; $G log -1 --format='%B' | grep -iE '^(co-authored-by|signed-off-by|generated|assisted)|noreply@|🤖' || echo "limpio"
```

El autor tiene que ser StphxLB y la búsqueda tiene que imprimir `limpio`. Si algo se coló, se arregla con `commit --amend` antes del push.

## Antes del commit

1. `./gradlew assembleDebug` termina bien.
2. El archivo de cambios de la versión dice `commit: done`. Ver [Cómo versionar este proyecto](03-versioning.md).

## Cómo se arma el mensaje

No se escribe a mano: se arma con las dos partes del archivo `02-lp3.X.Y-commit.md`, primero el título y después la descripción, separados por una línea en blanco.

```bash
V=lp3.3.4
F=versions/lp3/$V/02-$V-commit.md
{ sed -n '/^## Título/,/^## Descripción/{/^```$/,/^```$/{/^```$/d;p}}' "$F"; echo; sed -n '/^## Descripción/,${/^```$/,/^```$/{/^```$/d;p}}' "$F"; } | $G commit -F -
$G tag $V
```

Así lo que entra al commit y lo que está documentado son el mismo texto.

## Las etiquetas

Cada versión lleva una etiqueta con su número, pegada a su commit. La rama avanza con cada commit; una etiqueta nunca se mueve.

## El push

**Un push por commit, en orden.** La consigna pide un historial de pushes de features, así que cada versión se sube sola, y su etiqueta justo después:

```bash
$G push origin lp3.3.4:main
$G push origin lp3.3.4
```

Para ver qué etiquetas existen de verdad en GitHub:

```bash
$G ls-remote --tags origin
```

**Si alguna vez se reescriben commits en local**, antes del push se revisa a dónde apunta cada etiqueta (`$G tag` y `$G rev-parse <etiqueta>`). Una vez en GitHub, reescribir obliga a un push forzado, y eso lo autoriza el propietario.

## Lo que queda fuera

El `.gitignore` deja fuera `build/`, `.gradle/`, `local.properties` y los archivos de Android Studio que dependen de cada PC. Claves, contraseñas y tokens nunca entran.
