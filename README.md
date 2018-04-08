# curcon_prototype_autorisatie
This is a prototype for the course TODSS and the CURCON project.

Tools Used:
- Eclipse IDE
- Oracle database (export in resources folder)
- Tomcat v8.5 server

This prototype shows the basics of autorization, 
combined with authentication and tokens.

This link is only accessible for user with the role : __admin__
```
http://localhost:4711/Authorisatie_Test/rest/cursus/admin
```
This link is always accessible, even when not logged in:

`
http://localhost:4711/Authorisatie_Test/rest/cursus/altijd
`


