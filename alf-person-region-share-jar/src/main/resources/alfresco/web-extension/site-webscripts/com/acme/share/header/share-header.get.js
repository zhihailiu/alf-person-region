
// add a use menu item for editing region

var userMenu = widgetUtils.findObject(model.jsonModel, "id", "HEADER_USER_MENU");

if (userMenu != null) {

	var menuItem = {
		id : "HEADER_USER_MENU_METADATA",
		name : "alfresco/header/AlfMenuItem",
		config : {
			id : "HEADER_USER_MENU_METADATA",
			label : "share.header.menu.region.label",
			targetUrl : getTargetUrl()
		}
	};

	userMenu.config.widgets.push(menuItem);
}

// Call remote webscript to get login user's cm:person nodeRef and compose URL
// to the edit page. The URL is relative to "http://host:port/share/page".
function getTargetUrl() {
	var connector = remote.connect("alfresco");
	
	var response = connector.call("/person-node/" + user.id);
	
	if (response.status == 200) {
		var json = JSON.parse(response);
		return "edit-metadata?nodeRef=workspace://SpacesStore/" + json.nodeId;
	}

	return null;
}
