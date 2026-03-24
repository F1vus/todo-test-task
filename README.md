# Todo Test Task

Prosty serwis REST TODO przygotowany do celów rekrutacji. Projekt używa Spring Boot, JPA, MapStruct oraz Lombok.

## Zawartość
- API do zarządzania zadaniami (CRUD)
- MapStruct do mapowania DTO <-> encje
- Flyway do migracji bazy danych
- Testy jednostkowe i integracyjne używające w pamięci H2

## Wymagania
- Java 21
- Maven (wrapper `./mvnw` jest dołączony)
- (opcjonalnie) Docker / Docker Compose — do uruchomienia Postgresa w środowisku produkcyjnym

## Budowanie
Zbuduj projekt lokalnie:

```bash
./mvnw clean package
```

## Uruchamianie aplikacji
Aby uruchomić aplikację z lokalnym PostgreSQL (przez `compose.yaml`)
```bash
docker compose compose.yaml up -d
./mvnw spring-boot:run
```

## Testy
Uruchom wszystkie testy (jednostkowe i integracyjne):

```bash
./mvnw test
```

Testy są skonfigurowane do używania bazy H2 w pamięci, więc nie wymagają Postgresa ani Dockera.

## Uwagi techniczne
- Migracje SQL znajdują się w `src/main/resources/db/migration` (Flyway).
- Proszę sprawdzić czy w projekcie dobrze skonfigurowany `.env` plik

## Najważniejsze pliki
- `src/main/java/net/f1v/todotesttask` — kod aplikacji
- `src/test/java/...` — testy
- `compose.yaml` — opcjonalny Docker Compose dla PostgreSQL

## Tabela endpointów API
Poniżej znajduje się szybka tabela najważniejszych endpointów REST dostępnych w aplikacji.

| Metoda | Ścieżka | Opis |
|---|---|---|
| POST | `/api/v1/tasks` | Utwórz nowe zadanie (body: `TodoTaskRequest`) — zwraca 201 z utworzonym zadaniem |
| GET | `/api/v1/tasks` | Pobierz stronę zadań (parametry Pageable) — zwraca stronę `TodoTaskResponse` |
| GET | `/api/v1/tasks/{id}` | Pobierz zadanie po identyfikatorze — zwraca 200 lub 404 jeśli nie istnieje |
| PUT | `/api/v1/tasks/{id}` | Zaktualizuj istniejące zadanie (body: `TodoTaskRequest`) — zwraca 200 lub 404 |
| DELETE | `/api/v1/tasks/{id}` | Usuń zadanie — zwraca 204 |

Sekcje oznaczone `(dev)` to pomocnicze endpointy przydatne w środowisku developerskim/testowym — nie powinny być wystawione publicznie w produkcji.

