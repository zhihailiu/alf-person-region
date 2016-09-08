function main() {
	var myUser = groups.getUser(url.templateArgs['username']);
	var myNodeRef = myUser.personNodeRef;
	model.data = {"nodeId": myNodeRef.id}; 
}

main();
