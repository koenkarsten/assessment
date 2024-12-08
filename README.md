# usage
Built with Java 21 combined with Maven in IntelliJ. Open the project and sync the `pom.xml` through Maven, from there on the `AssessmentApplication` should be runnable. Additional runnable tests can be found in `src/main/test/...`. The following endpoints are available after running:
- `GET/PUT/POST /api/1/devices`
- `GET/DELETE /api/1/devices/<uuid>`
- `GET/POST /api/1/detections`

These can be accessed with the following BASIC Authentication credentials: `username: john.doe@fakemail.com` & `password: 7PTZ15Xxx2fN0saGRLt68TeevpdaywyY`. How these will be added depends on the client you use, but is should result in a `Authorization` Header containing `Basic am9obi5kb2VAZmFrZW1haWwuY29tOjdQVFoxNVh4eDJmTjBzYUdSTHQ2OFRlZXZwZGF5d3lZ`.

# assessment
For this assessment I tried striking a balance between sticking to the minimal requirements (just 2 relatively easy endpoints) and showcasing some additional skills. I felt including the following things as well makes for a more complete demonstration of Java / Spring understanging:
- **CRUD** the DeviceController and all it related classes allow for basic CRUD operations of devices. For Detections I just implemented the 2 endpoints as instructed.
- **Database** The h2 in memory database has been populated with some dummy data for easy assessment of application functionality, normally this would not be the case for an application destined for production.
- **Security** Included Spring Boot Securty to highlight I have considered that this application needs to be secured by default. I did keep it very simple as I believe security should be centralised, more information on that in the next steps.
- **Tracing** Included a simple Tracing setup that currently logs every incoming request and logs the response code. I think the pattern used is nice but this is another aspect I believe should be standardised among all applications. That is why I kept it basic but hopefully it demonstrated my knowledge regardless. 

## Next Steps
- **Security, Packaging and Pipelines:** With regard to security I find it very important to make sure this is standardised for all similar applications. By centralising HTTP Authentication and Authorisation to a dedicated application you prevent fragmenting. Similarly you want to make additional security steps like code analysis and image scanning a default part of the CI/CD pipeline. As this is very dependent on the architecture at hand I have considered this out of scope for now. I have included a very simple hardcoded username/password BASIC login system with just 1 admin role  as an example in this app, but do not consider this a viable solution.
- **Representation**: In general it is good to not bleed internal models (like Device and Detection) to external API's, as any internal changes will also trigger API changes. A common way would be to include Representation versions (like DeviceRepresentation and DetectionRepresentation). But for this assessment I chose to keep it simple, for a production application I would include this additional layer in the codebase.

