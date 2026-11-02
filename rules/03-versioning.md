---
title: versioning
updated: 2026-11-02
status: draft
---

# Cómo versionar este proyecto

Obligatorio para todo agente de IA. **Ninguna sesión que toque archivos termina sin dejar escrita su versión.** No se pide permiso para escribirla: se escribe y se avisa en una línea.

El registro se escribe **en palabras que se entienden sin abrir el código**: qué cambió, no qué rutas se tocaron. Cada documento se nombra por su título, el `# Título` de adentro; nunca por su ruta.

## El número

```
lp3 . 1 . 0
 │    │   └── Retoque  | documentación, un arreglo o una actualización de herramientas.
 │    └────── Feature  | una funcionalidad nueva que se ve en la app.
 └─────────── PA       | el proceso de aprendizaje del curso en el que se hizo (lp3 = PA3).
```

**`lp` viene de *learning process***, el nombre en inglés de los Procesos de Aprendizaje (PA) del curso. Los nombres del proyecto van en inglés; por eso no es `pa3`. Ver [Versiones](../versions/00-versions-index.md).

**Para subirlo:** manda el nivel más alto que se tocó, y lo que queda a su derecha vuelve a 0.

| Lo que se hizo | De | A |
|---|---|---|
| Corregir un texto de la documentación | `lp3.3.2` | `lp3.3.3` |
| Agregar una funcionalidad | `lp3.3.4` | `lp3.4.0` |
| Empezar una PA nueva sobre este proyecto | `lp3.4.0` | `lp4.0.0` |

Si una sesión tiene cambios de varios niveles, **gana el más alto**. Una funcionalidad nueva con su documentación es `Feature`, no `Retoque`. Si dudas entre dos niveles, el más alto.

**Una PA nueva** empieza en `lp<N>.0.0 | Base`, sobre el mismo historial. Su carpeta en `versions/` es `lp<N>/`.

## El tipo

El título del commit es el número y una palabra que dice qué es: `lp3.2.0 | Feature II`.

| Tipo | Cuándo | Nivel |
|---|---|---|
| `Base` | El punto de partida de una PA | `lpN.0.0` |
| `Feature` | Una funcionalidad nueva | El segundo número |
| `Docs` | Solo documentación | El tercer número |
| `Fix` | Un arreglo | El tercer número |
| `Build` | Herramientas de compilación o librerías | El tercer número |

**Si un tipo se repite en la misma PA, lleva número romano.** La primera vez va sin número, la segunda con `II`, la tercera con `III`, y así. Vale para todos los tipos menos `Base`, que hay una sola por PA: `Feature`, `Feature II`, `Feature III`; `Docs`, `Docs II`; `Fix`, `Fix II`. Para saber cuál toca, se cuentan en `versions/lp3/00-lp3-index.md` las versiones de ese tipo que ya existen. Cada PA vuelve a empezar: la primera `Docs` de la PA4 es `Docs`, sin número.

## Dónde vive

```
versions/
├── 00-versions-index.md          cómo funciona el sistema
└── lp3/
    ├── 00-lp3-index.md           la lista de versiones de la PA3, de la más nueva a la más vieja
    └── lp3.1.0/
        ├── 01-lp3.1.0-changes.md     qué cambió, en simple
        └── 02-lp3.1.0-commit.md      lo que va en el commit
```

## Los símbolos

| Símbolo | Cuándo |
|---|---|
| `[+]` | Se agregó algo que no existía |
| `[*]` | Se cambió algo que ya existía |
| `[-]` | Se quitó algo |
| `[~]` | Se movió o renombró algo, sin cambiar su contenido |
| `[=]` | Sincronización mecánica: la versión en el README, en el índice y en el registro de cambios |

## Cómo se escribe la descripción

Es la parte que importa. Se lee en GitHub, meses después, sin contexto.

**Se agrupa por área.** Una línea con el símbolo y el área tocada, terminada en dos puntos, y **justo debajo, sin línea en blanco**, las líneas de lo que cambió. La línea en blanco solo va entre un grupo y el siguiente.

```
[+] Componentes:
- HazeDialog | el aviso de la app con los colores de Ember

[*] Pantallas:
- Hacer pedido pregunta antes de vaciar el carrito
```

Así **no** se hace:

```
[*] app/src/main/java/.../OrderActivity.kt | add dialog
```

**Reglas de escritura:**

- **En español.**
- **Un grupo por símbolo y área.** Aunque el área tenga un solo cambio, va agrupada.
- **Una línea por cambio**, empezando con `-`. Nunca con `*`, que se confunde con el símbolo `[*]`.
- Si algo necesita aclaración, va después de un `|`: `- HazeTopBar | ahora muestra el carrito`.
- Los componentes y las pantallas se nombran como en el código (`HazeScaffold`, la carta); las rutas de archivo no.
- **Nada de raya larga.**

## El protocolo del agente

Al terminar cualquier sesión que tocó archivos:

**1. Busca la versión actual.** Abre la carpeta de la PA más alta en `versions/` y toma la carpeta de versión con el número más alto. Compara número por número, no como texto: `lp3.10.0` es mayor que `lp3.9.0`.

**2. Mira si ya se hizo el commit.** Abre su archivo de cambios y lee el campo `commit:` de la cabecera.

- `commit: pending` | la versión está **abierta**. **Agrega tus cambios ahí**, no crees otra.
- `commit: done` | la versión está **cerrada**. **Crea la carpeta de la versión siguiente.**

**3a. Si estaba en `pending`:** agrega tus líneas y **vuelve a calcular el número desde cero**, con todo lo acumulado. Si el número cambia, renombra la carpeta y los dos archivos, y actualiza el `title` de la cabecera y el `# Título`.

**3b. Si estaba en `done`:** crea `versions/lp3/lp3.X.Y/` con los dos archivos, con las plantillas de abajo.

**4. Actualiza los índices.** Agrega la línea de la versión arriba del todo en `versions/lp3/00-lp3-index.md`, sube el número que muestra `README.md` y, si cambió algo de la app, agrega su entrada en el registro de cambios (`docs/05-haze-changelog.md`). Eso va bajo `[=]` en el commit.

**5. Cierra la versión ANTES del commit.** Cambia `commit: pending` por `commit: done` y **después** haz `git add`. Nunca al revés: un commit con `pending` deja para siempre una versión que dice "sin commit" cuando sí lo tuvo.

## Plantilla | archivo de cambios

Nombre: `01-lp3.X.Y-changes.md`

~~~markdown
---
title: lp3.X.Y-changes
updated: AAAA-MM-DD
status: current
commit: pending
type: base | feature | docs | fix | build
---

# lp3.X.Y | Tipo (con su número romano si se repite)

Una frase que dice de qué trata esta versión.

## Qué cambió

[+] Área tocada:
- Nombre de la cosa | qué es, en simple

[*] Otra área:
- Nombre de la cosa | qué cambió y por qué importa

## Por qué

Dos o tres frases: qué problema resolvió. Si es obvio, se borra esta sección.
~~~

## Plantilla | archivo del commit

Nombre: `02-lp3.X.Y-commit.md`

~~~markdown
---
title: lp3.X.Y-commit
updated: AAAA-MM-DD
status: current
---

# lp3.X.Y | Descripción del commit

## Título

```
lp3.X.Y | Tipo
```

## Descripción

```
[+] Área tocada:
- Nombre de la cosa | qué es
```
~~~

El mensaje del commit se arma con estas dos partes. Ver [Git](04-git.md).
