# File Configuration Parser

Parser for configuration files. 
Built on Java.

## Installation

### Requirements

#### Java 8+
The project was developed with Java 8.

#### Maven
You'll need maven for compile, build, test or run the project. 

### Steps

- Get the project by cloning it from GitHub Repo [link](https://github.com/kurayamigin/file-configuration-parser.git)
- Try compiling the project wih `mvn compile`
- Try running tests with `mvn test`
- Try executing application with:`mvn exec:java "-Dexec.args=$FILE_PATH"`, 
where `$FILE_PATH` is the file configuration to be parsed. If no argument provided, then will use a config file that
is already on the project (./src/main/resources/test/configs/config).
- The $FILE_PATH can be an absolute path.


### Additional notes
The source code involves the next topics:
- OOP.
- SOLID.
- Design Patters (Singleton, Factory, Dependency Inversion, Provider, Iterator).

- Async (Multi-threading/Futures).
- Exceptions Handling / Custom Exceptions.
- Creating Generics.

- Unit Tests.
- Mocking.
- Parametrized Tests.
- varargs.
- Stacks.
- i18n (internationalization).
- Enums.

- Maven (POM file).
- Dependencies.
- Plugins.
- creating properties in POM.
