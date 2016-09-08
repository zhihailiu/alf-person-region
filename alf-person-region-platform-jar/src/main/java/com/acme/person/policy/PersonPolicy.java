package com.acme.person.policy;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

/**
 * Adds acme:regional aspect when cm:person is created.
 *
 */
public class PersonPolicy implements NodeServicePolicies.OnCreateNodePolicy {

	static Logger log = Logger.getLogger(PersonPolicy.class);

	/**
	 * namespace
	 */
	public static final String ACME_CONTENT_MODEL_URI = "http://www.acme.org/model/content/1.0";

	public static final QName ASPECT_ACME_REGIONAL = QName.createQName(ACME_CONTENT_MODEL_URI, "regional");

	private NodeService nodeService;

	private PolicyComponent policyComponent;

	private Behaviour onCreateNodeBehavior;

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setPolicyComponent(PolicyComponent policyComponent) {
		this.policyComponent = policyComponent;
	}

	/**
	 * Initialize/register.
	 */
	public void init() {
		this.onCreateNodeBehavior = new JavaBehaviour(this, "onCreateNode", NotificationFrequency.TRANSACTION_COMMIT);
		this.policyComponent.bindClassBehaviour(NodeServicePolicies.OnCreateNodePolicy.QNAME, ContentModel.TYPE_PERSON,
				this.onCreateNodeBehavior);
	}

	@Override
	public void onCreateNode(ChildAssociationRef childAssocRef) {
		NodeRef childRef = childAssocRef.getChildRef();
		this.nodeService.addAspect(childRef, ASPECT_ACME_REGIONAL, null);
	}
}
