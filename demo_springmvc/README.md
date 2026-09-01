# Backend — Task Manager

Backend REST de l'application de gestion de tâches.

L'application est développée avec Java, Spring MVC, MyBatis et Oracle XE.

## Technologies

* Java 8
* Spring MVC 5
* MyBatis
* Maven
* Apache Tomcat
* Oracle XE
* JDBC
* JUnit

## Architecture

```text
REST Controller
      ↓
   Service
      ↓
   Mapper
      ↓
   MyBatis
      ↓
   Oracle XE
```

## Fonctionnalités

Le backend expose une API REST permettant de :

* récupérer les tâches;
* créer une tâche;
* modifier une tâche;
* supprimer une tâche.

## API REST

### Récupérer les tâches

```http
GET /demo_springmvc/api/tasks
```

### Créer une tâche

```http
POST /demo_springmvc/api/tasks
```

Exemple :

```json
{
  "title": "Apprendre MyBatis",
  "completed": false
}
```

### Modifier une tâche

```http
PUT /demo_springmvc/api/tasks/{id}
```

### Supprimer une tâche

```http
DELETE /demo_springmvc/api/tasks/{id}
```

## Base de données

Le backend utilise Oracle XE.

La table principale est :

```text
TASK
├── ID
├── TITLE
└── COMPLETED
```

Une séquence Oracle est utilisée pour générer les identifiants :

```text
TASK_SEQ
```

L'application utilise un utilisateur Oracle dédié à l'application.

## Configuration

Les paramètres de connexion à Oracle sont externalisés dans `application.properties`.

Exemple :

```properties
oracle.url=jdbc:oracle:thin:@//localhost:1521/XE
oracle.username=YOUR_USERNAME
oracle.password=YOUR_PASSWORD
```

## Compilation

Depuis le dossier `backend` :

```bash
.\mvnw clean package
```

Le projet produit un fichier WAR :

```text
target/demo_springmvc.war
```

## Déploiement

Le WAR peut être déployé sur Apache Tomcat (en le collant dans C:\apache-tomcat-XXXX\webapps).

Le backend est alors accessible, selon la configuration Tomcat, à :

```text
http://localhost:8082/demo_springmvc
```

L'API est disponible à :

```text
http://localhost:8082/demo_springmvc/api/tasks
```

## Tests

Le projet contient des tests d'intégration exécutés avec JUnit.

### Tests de structure

Ils vérifient que la structure Oracle attendue est toujours présente.

Ils permettent notamment de détecter des modifications incompatibles de :

* types de colonnes;
* tailles;
* contraintes `NOT NULL`;
* clé primaire;
* valeurs par défaut.

### Tests MyBatis

Les quatre opérations CRUD sont testées contre Oracle :

```text
SELECT
INSERT
UPDATE
DELETE
```

Les tests créent leurs propres données de test et les suppriment ensuite.

## Exécuter les tests

```bash
.\mvnw test
```

Une connexion fonctionnelle à Oracle XE est nécessaire.

## Structure simplifiée

```text
src/
├── main/
│   ├── java/
│   │   └── com/example/demo/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── mapper/
│   │       ├── model/
│   │       └── service/
│   │
│   └── resources/
│       └── application.properties
│
└── test/
    └── java/
        └── com/example/demo/
            ├── TaskDatabaseStructureTest.java
            └── mapper/
                └── TaskMapperOracleTest.java
```

## Frontend

Le frontend Angular correspondant se trouve dans :

```text
../demo-angular
```

Consulter son README pour les instructions de lancement.
