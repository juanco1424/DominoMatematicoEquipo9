# DominoMatematicoEquipo9

# Guía de Git
Lo primero que se debe tener es git bash en el equipo/máquina les dejo un link si es necesario para que puedan instalarlo facil y sin complicaciones con este es el video [Link](https://www.youtube.com/watch?v=p9S1wSChtSo&ab_channel=Roelcode).

Recuerden que para instalarlo correctamente deben validar que particion de windows tiene y cuantos bits es.

## Clonar un Repositorio
Antes de clonar deben lanzar este comando para que les cree un repo local con el fin de poder usar el siguiente comando y este se configure.
```bash
git init
```
Para clonar un repositorio desde GitHub a tu máquina local, utiliza el siguiente comando:

```bash
git clone https://github.com/juanco1424/DominoMatematicoEquipo9.git
```

Ya cuando lo clonan en la terminal pueden ver que les sale la url del directorio más **(master)** o **(main)**.

Para eso van a usar el siguiente comando que lo que van a hacer es crear una rama, esta "rama" es simplemente un espacio de trabajo para que ustedes puedan hacer lo que quieran como quieran sin dañar la copio de "seguridad"

Con el siguiente comando lo pueden hacer (ojo donde dice nombre de la rama es la que ustedes quieran no es necesario dejarlo igual): 
```bash
git branch <nombre-de-la-rama>
```

Listo la crearon, pero no se cambiaron a ella lo que deben hacer es lanzar el comando:
```bash
git checkout <nombre-de-la-rama-creada>
```

Para saber en qué rama están parados solo deben escribir este comando y validar donde esté el "*":
```bash
git branch -a
```

Si terminan y quieren que les quede guarda una copia de lo suyo a la nube es con los siguientes comandos en orden:
```bash
git add .
```
Este se usa con el fin de que todos los archivos que sean modificados se agregen al espacio del trabajo (eso es otro tema, no se maten la cabeza entendiendo eso)

```bash
git commit -m "Descripción de los cambios"
```
Este es para que cuando se agreguen al espacio de trabajo, le des un comentario de que estan agregando, "una nueva función" o "se elimina función que no se usa", etc.

Ya para finalizar y subir los cambios usan este comando: 

```bash
git push origin nombre-de-la-rama
```