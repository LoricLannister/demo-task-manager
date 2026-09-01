# Task Manager

Application web full-stack de gestion de tâches développée avec Angular, Spring MVC, MyBatis et Oracle XE.

Le projet permet de créer, consulter, modifier, supprimer et marquer comme terminées des tâches.

## Architecture

```text
┌──────────────────────┐
│       Angular        │
│      Frontend        │
└──────────┬───────────┘
           │ HTTP / REST
           ▼
┌──────────────────────┐
│     Spring MVC       │
│       Backend        │
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐
│       MyBatis        │
│    Persistence       │
└──────────┬───────────┘
           │ JDBC
           ▼
┌──────────────────────┐
│      Oracle XE       │
│      Database        │
└──────────────────────┘
```

## Fonctionnalités

* Affichage des tâches
* Création d'une tâche
* Modification d'une tâche
* Suppression d'une tâche
* Marquage d'une tâche comme terminée
* Communication frontend/backend via API REST
* Persistence des données dans Oracle XE

## Technologies

### Frontend

* Angular
* TypeScript
* HTML
* CSS
* HttpClient
* Forms / `ngModel`

### Backend

* Java 8
* Spring MVC
* MyBatis
* Maven Wrapper
* Apache Tomcat
* Oracle XE

### Tests

* JUnit
* Tests d'intégration avec Oracle
* Vérification de la structure de la base
* Tests des opérations CRUD MyBatis

## Structure du projet

```text
demo-task-manager/
│
├── demo_springmvc/
│   └── Application Angular
│
├── demo-angular/
│   └── Application Spring MVC / MyBatis
│
└── README.md
```

## Installation

### 1. Base de données

Créer une base Oracle XE et un utilisateur dédié à l'application.

Créer ensuite la table `TASK` ainsi que la séquence utilisée pour générer les identifiants.

La structure attendue est notamment :

```text
TASK
├── ID
├── TITLE
└── COMPLETED
```

### 2. Backend

Consulter le [README du backend](demo_springmvc/README.md) pour les instructions détaillées.

Le backend utilise Maven Wrapper pour la compilation et le packaging :

```bash
.\mvnw clean package
```

Les tests peuvent être exécutés avec :

```bash
.\mvnw test
```

Les tests nécessitent une connexion fonctionnelle à Oracle XE.

### 3. Frontend

Consulter le [README du frontend](demo-angular/README.md).

Installation des dépendances :

```bash
npm install
```

Puis lancement de l'application :

```bash
npm start
```

L'application Angular est alors accessible sur :

```text
http://localhost:4200
```

## Tests

Le projet contient deux catégories principales de tests.

### Tests de structure Oracle

Ils vérifient que la structure de la table `TASK` correspond aux attentes de l'application.

Par exemple, une modification accidentelle de :

```sql
TITLE VARCHAR2(255)
```

vers :

```sql
TITLE VARCHAR2(500)
```

est détectée par les tests.

### Tests MyBatis

Les opérations suivantes sont testées contre une véritable instance Oracle :

* `SELECT`
* `INSERT`
* `UPDATE`
* `DELETE`

Les données créées par les tests sont nettoyées après leur utilisation.

## Configuration

Les paramètres de connexion à Oracle sont externalisés dans la configuration de l'application.

Les identifiants réels de la base ne doivent pas être commités dans Git.

La section configuration du readme backend documente les paramètres nécessaires.

## Objectif du projet

Ce projet a été réalisé comme exercice pratique de développement logiciel full-stack afin de mettre en œuvre :

* une architecture frontend/backend séparée ;
* une API REST ;
* une couche de persistence avec MyBatis ;
* une base de données Oracle ;
* des tests automatisés ;
* des tests d'intégration avec une base de données réelle ;
* une gestion propre de la configuration.

## Auteur

Projet personnel réalisé dans le cadre de la montée en compétences en Java 8 + Spring MVC + JUnit + MyBatis + Oracle en vue de pouvoir travailler sur le projet CAMCIS.
