package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.dossiersync.model.DossierSyncDataModel;
import org.opencps.api.dossiersync.model.DossierSyncSendingModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.model.DossierSync;

public class DossierSyncUtils {
	
	public static List<DossierSyncDataModel> mappingToData(List<DossierSync> ls) {

		List<DossierSyncDataModel> out = new ArrayList<DossierSyncDataModel>();

		for (DossierSync dsync : ls) {
			DossierSyncDataModel model = new DossierSyncDataModel();

			model.setDossierSyncId(dsync.getDossierSyncId());
			model.setCreateDate(
					APIDateTimeUtils.convertDateToString(dsync.getCreateDate(), APIDateTimeUtils._NORMAL_PARTTERN));
			model.setDossierId(dsync.getDossierId());
			model.setDossierReferenceUid(dsync.getDossierReferenceUid());
			model.setCreateDossier(dsync.getCreateDossier() ? 1 : 0);
			model.setMethod(dsync.getMethod());
			model.setClassPK_0020(dsync.getClassPK());
			model.setFileReferenceUid(dsync.getFileReferenceUid());

			out.add(model);
		}

		return out;
	}
	
	public static DossierSyncSendingModel mappingToSending(DossierSync dsync) {
		DossierSyncSendingModel out = new DossierSyncSendingModel();
		
		out.setDossierId(dsync.getDossierId());
		out.setDossierReferenceUid(dsync.getDossierReferenceUid());
		out.setCreateDossier(dsync.getCreateDossier() ? 1 : 0);
		out.setMethod(dsync.getMethod());
		out.setClassPK_0020(dsync.getClassPK());
		out.setFileReferenceUid(dsync.getFileReferenceUid());
		
		return out;
	}
}
