function main()
{
   // Need to know what type of node this is - document or folder
   var nodeRef = page.url.args.nodeRef,
      nodeType = "document",
      fileName = "",
      connector = remote.connect("alfresco"),
      result = connector.get("/slingshot/edit-metadata/node/" + nodeRef.replace(":/", ""));

   if (result.status == 200)
   {
      var metadata = JSON.parse(result);
      nodeType = metadata.node.isContainer ? "folder" : "document";
      fileName = metadata.node.fileName;
      
      // acuity customization
      type = metadata.node.type;
   }
   model.nodeRef = nodeRef;
   model.nodeType = nodeType;
   model.fileName = fileName;

   // Display user friendly information. Otherwise it shows nodeRef.
   if (type == "cm:person") {
	   model.fileName = msg.get("share.edit.metadata.region.label");
   }
}

main();