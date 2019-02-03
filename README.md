# Jooq Immutable POJOs Copy Constructor Byte Array Issue
Immutable pojos generated by jooq that use byte arrays can be modified by the copy constructor.

## Setup
```bash
docker-compose up
docker exec -it sample-db /bin/bash

# In the new terminal
# psql -U sample
# CREATE TABLE foobar (id BIGINT PRIMARY KEY NOT NULL, data BYTEA NOT NULL );
# \q

mvn clean install
```

## Issue
The copy constructor of an immutable pojo that has a byte array field can be mutated.

```java
public class Foobar {
    private final Long   id;
    private final byte[] data;

    public Foobar(Foobar value) {
        this.id = value.id;
        this.data = value.data;
    }

    public Foobar(Long id, byte[] data) {
        this.id = id;
        this.data = data;
    }
}
```

```java
byte[] bytes = {1, 2, 3, 4};
Foobar f = new Foobar(1L, bytes);
Foobar copy = new Foobar(f);
copy.getData()[0] = 10;

System.out.println(f.getData()[0])    // 10
System.out.println(copy.getData()[0]) // 10
```