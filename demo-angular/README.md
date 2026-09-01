# Frontend — Task Manager

Frontend Angular de l'application full-stack de gestion de tâches.

L'application permet de consulter, créer, modifier, supprimer et marquer comme terminées les tâches stockées dans la base Oracle via l'API REST du backend Spring MVC.

## Technologies

* Angular
* TypeScript
* HTML5
* CSS3
* Angular Forms
* Angular HttpClient
* REST API

## Versions utilisées

Ce projet utilise volontairement une stack Angular ancienne afin de rester compatible avec l'environnement technique et les contraintes de compatibilité du backend du projet.

| Outil       |  Version |
| ----------- | -------: |
| Node.js     | `8.17.0` |
| npm         | `6.13.4` |
| Angular CLI |  `1.7.3` |

> **Important :** ces versions correspondent à l'environnement utilisé pour développer et exécuter ce projet. Il n'est pas recommandé de remplacer directement cette stack par les versions actuelles d'Angular/Node sans effectuer une migration du projet.

Angular documente officiellement des matrices de compatibilité entre ses versions, Node.js, TypeScript et RxJS. Les anciennes versions d'Angular ne sont plus les versions recommandées pour de nouveaux projets, mais elles peuvent rester nécessaires pour maintenir une application existante.

## Prérequis

Avant de lancer le frontend, vérifier les versions installées :

```bash
node -v
npm -v
ng version
```

L'environnement attendu est :

```text
Node.js    v8.17.0
npm        6.13.4
Angular CLI 1.7.3
```

## Installation

Depuis le dossier `frontend` :

```bash
npm install
```

Cette commande installe les dépendances déclarées dans `package.json`.

Les dépendances d'un projet Angular sont gérées par npm à partir du `package.json`.

## Lancement

Le projet est configuré pour être lancé avec :

```bash
npm start
```

Cette commande utilise le script `start` défini dans le `package.json`.

Le frontend est normalement accessible à :

```text
http://localhost:4200
```

Le serveur de développement recharge automatiquement l'application lors des modifications du code.

## Communication avec le backend

Le frontend communique avec l'API REST Spring MVC.

Backend :

```text
http://localhost:8082/demo_springmvc
```

API des tâches :

```text
http://localhost:8082/demo_springmvc/api/tasks
```

### Endpoints utilisés

| Méthode | Endpoint          | Fonction             |
| ------- | ----------------- | -------------------- |
| GET     | `/api/tasks`      | Récupérer les tâches |
| POST    | `/api/tasks`      | Créer une tâche      |
| PUT     | `/api/tasks/{id}` | Modifier une tâche   |
| DELETE  | `/api/tasks/{id}` | Supprimer une tâche  |

Le backend doit être démarré avant d'utiliser les fonctionnalités nécessitant l'accès aux données.

## Fonctionnalités

L'interface permet de :

* afficher la liste des tâches ;
* ajouter une tâche ;
* modifier le titre d'une tâche ;
* marquer une tâche comme terminée ;
* supprimer une tâche ;
* afficher un état de chargement ;
* afficher les erreurs de communication avec le backend ;
* afficher un message lorsque la liste est vide.

L'ajout d'une tâche peut également être effectué avec la touche `Entrée`.

## Architecture simplifiée

```text
Angular Component
       │
       ▼
Task Service
       │
       │ HTTP
       ▼
Spring MVC REST API
       │
       ▼
MyBatis
       │
       ▼
Oracle XE
```

## Structure du frontend

```text
demo-angular/
│
├── src/
│   ├── app/
│   │   ├── services/
│   │   │   └── task.service.ts
│   │   │
│   │   ├── app.component.ts
│   │   ├── app.component.html
│   │   └── app.component.css
│   │
│   └── ...
│
├── package.json
├── angular-cli.json
└── README.md
```

## Démarrage de l'application complète

### 1. Démarrer Oracle XE

Oracle XE doit être disponible et la base doit contenir la table `TASK`.

### 2. Démarrer le backend

Le backend Spring MVC doit être déployé sur Tomcat.

URL :

```text
http://localhost:8082/demo_springmvc
```

### 3. Démarrer le frontend

Depuis `frontend` :

```bash
npm start
```

Puis ouvrir :

```text
http://localhost:4200
```

## Dépannage

### Vérifier Node.js

```bash
node -v
```

Doit retourner :

```text
v8.17.0
```

### Vérifier npm

```bash
npm -v
```

Doit retourner :

```text
6.13.4
```

### Vérifier Angular CLI

```bash
ng version
```

La version utilisée pour ce projet est :

```text
1.7.3
```

### Le frontend affiche une erreur de connexion

Vérifier que le backend est démarré et accessible à :

```text
http://localhost:8082/demo_springmvc/api/tasks
```

Vérifier également que le backend autorise les requêtes provenant de :

```text
http://localhost:4200
```

## Compatibilité

Le choix de Node.js `8.17.0`, npm `6.13.4` et Angular CLI `1.7.3` est volontaire.

Cette application fait partie d'une stack plus ancienne composée notamment de :

```text
Angular
Spring MVC 5
Java 8
MyBatis
Oracle XE
Tomcat
```

Le projet privilégie donc la **reproductibilité de l'environnement utilisé pour son développement** plutôt qu'une mise à niveau automatique vers les versions actuelles.

Une migration vers une version moderne d'Angular nécessiterait une étude de compatibilité et potentiellement des modifications du code, des dépendances et de l'outillage.

## Backend

Le backend correspondant se trouve dans :

```text
../backend
```

Consulter [`../demo_springmvc/README.md`](../demo_springmvc/README.md) pour les informations concernant :

* Spring MVC;
* MyBatis;
* Oracle XE;
* la configuration de la base de données;
* les tests JUnit;
* le déploiement Tomcat.

## Projet complet

Le frontend fait partie du projet **Task Manager**, dont la documentation principale se trouve à la racine du repository :

```text
../README.md
```
