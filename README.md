# usage
Built with Java 21 combined with Maven in IntelliJ.
Open the project and sync the `pom.xml` through Maven, from there on the `AssessmentApplication` should be runnable.
Additional runnable tests can be found in `src/main/test/...`. The following endpoints are available after running:
- `GET/PUT/POST /api/1/devices`
- `GET/DELETE /api/1/devices/<uuid>`
- `GET/POST /api/1/detections`

These can be accessed with the following BASIC Authentication credentials: `john.doe:7PTZ15Xxx2fN0saGRLt68TeevpdaywyY`.

Also some additional endpoints are enabled holding Metrics, OpenAPI Specification and Swagger UI:
- `http://localhost:8080/actuator/metrics` (requires Basic Auth)
- `http://localhost:8080/v3/api-docs`
- `http://localhost:8080/swagger-ui/index.html`

# assessment
For this assessment I tried striking a balance between sticking to the minimal requirements (just 2 relatively easy endpoints) and showcasing some additional skills.
I felt including the following things as well makes for a more complete demonstration of Java / Spring understanding:
- **CRUD** the DeviceController and all it related classes allow for basic CRUD operations of devices. For Detections I just implemented the 2 endpoints as instructed.
- **Database** The h2 in memory database has been populated with some dummy data for easy assessment of application functionality, normally this would not be the case for an application destined for production.
- **Security** Included Spring Boot Security to highlight I have considered that this application needs to be secured by default. I did keep it very simple as I believe security should be centralised, more information on that in the next steps.
- **Tracing** Included a simple Tracing setup that currently logs every incoming request and logs the response code. I think the pattern used is nice but this is another aspect I believe should be standardised among all applications. That is why I kept it basic, but hopefully it demonstrated my knowledge regardless. 
- **Metrics** Spring Boot Actuator is used for exposing application health and metircs.
- **Documentation** OpenAPI Specification and Swagger UI is also included.
