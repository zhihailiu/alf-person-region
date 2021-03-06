# Add Region to Person
- Alfresco AIO project generated by Alfresco SDK 3.0.0-Beta3
- Run with `mvn clean install -DskipTests=true alfresco:run` or `./run.sh`

## Requirement
As a user, I can specify Region in my profile by selecting from Region category.

## Use Case
Platform
- Create acme:regional aspect with acme:region property in content model. acme:region property is of type d:category and its value is from Region category as defined in Admin Tools > Category Manager.
- Create a policy to add acme:regional aspect to cm:person object when a new user is created

Share
- "the user profile does not leverage the Alfresco form service to render its form" (http://ecmarchitect.com/archives/2012/02/27/1555). Since acme:region is d:category type, we need the form service to render the category form control category.ftl. I would have to recreate the user profile page to add Region. Instead, I decided to reuse the Edit Metadata page for selecting Region.
- Add a user menu "Region" to link to the page
