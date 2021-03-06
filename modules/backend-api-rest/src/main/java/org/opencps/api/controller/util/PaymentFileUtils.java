package org.opencps.api.controller.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.api.paymentfile.model.PaymentFileInputModel;
import org.opencps.api.paymentfile.model.PaymentFileModel;
import org.opencps.api.paymentfile.model.PaymentFileSearchTemplateModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileVersionLocalServiceUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

public class PaymentFileUtils {

	/**
	 * Mapping documents with Object PaymentFileModel
	 * 
	 * @param documents
	 * @return List<PaymentFileModel>
	 */
	public static List<PaymentFileModel> mappingToPaymentFileModel(List<Document> documents) throws ParseException {

		if (documents == null) {
			return null;
		}

		List<PaymentFileModel> results = new ArrayList<PaymentFileModel>();

		for (Document doc : documents) {
			PaymentFileModel model = new PaymentFileModel();

			// model.setCreateDate(APIDateTimeUtils.convertDateToString(doc.getDate(PaymentFileTerm.CREATE_DATE)));
			// model.setModifiedDate(APIDateTimeUtils.convertDateToString(doc.getDate(PaymentFileTerm.MODIFIED_DATE)));
			model.setCreateDate(doc.get(PaymentFileTerm.CREATE_DATE));
			model.setModifiedDate(doc.get(PaymentFileTerm.MODIFIED_DATE));
			model.setReferenceUid(doc.get(PaymentFileTerm.REFERENCE_UID));
			model.setGovAgencyCode(doc.get(PaymentFileTerm.GOV_AGENCY_CODE));
			model.setGovAgencyName(doc.get(PaymentFileTerm.GOV_AGENCY_NAME));
			model.setApplicantName(doc.get(PaymentFileTerm.APPLICANT_NAME));
			model.setApplicantIdNo(doc.get(PaymentFileTerm.APPLICANT_ID_NO));
			// model.setIsNew(paymentFile.getIsNew());
			model.setPaymentFee(doc.get(PaymentFileTerm.PAYMENT_FEE));
			model.setPaymentAmount(GetterUtil.getLong(doc.get(PaymentFileTerm.PAYMENT_AMOUNT)));
			model.setPaymentNote(doc.get(PaymentFileTerm.PAYMENT_NOTE));
			model.setBankInfo(doc.get(PaymentFileTerm.BANK_INFO));
			model.setEpaymentProfile(doc.get(PaymentFileTerm.EPAYMENT_PROFILE));
			model.setPaymentStatus(Integer.parseInt(doc.get(PaymentFileTerm.PAYMENT_STATUS)));
			model.setPaymentMethod(doc.get(PaymentFileTerm.PAYMENT_METHOD));
			// model.setConfirmDatetime(APIDateTimeUtils.convertDateToString(doc.getDate(PaymentFileTerm.CONFIRM_DATETIME)));
			model.setConfirmPayload(doc.get(PaymentFileTerm.CONFIRM_PAYLOAD));
			DLFileVersion dlFilePayLoad = getFileInfo(
					GetterUtil.getLong(doc.get(PaymentFileTerm.CONFIRM_FILE_ENTRY_ID)));
			if (dlFilePayLoad != null) {
				model.setConfirmFileType(dlFilePayLoad.getExtension());
				model.setConfirmFileSize(dlFilePayLoad.getSize());
			} else {
				model.setConfirmFileType(StringPool.BLANK);
				model.setConfirmFileSize(0L);
			}
			model.setConfirmNote(doc.get(PaymentFileTerm.CONFIRM_NOTE));
			// model.setApproveDatetime(APIDateTimeUtils.convertDateToString(doc.getDate(PaymentFileTerm.APPROVE_DATETIME)));
			model.setAccountUserName(doc.get(PaymentFileTerm.ACCOUNT_USER_NAME));
			model.setGovAgencyTaxNo(doc.get(PaymentFileTerm.GOV_AGENCY_TAX_NO));
			model.setInvoiceTemplateNo(doc.get(PaymentFileTerm.INVOICE_TEMPLATE_NO));
			model.setInvoiceIssueNo(doc.get(PaymentFileTerm.INVOICE_ISSUE_NO));
			model.setInvoiceNo(doc.get(PaymentFileTerm.INVOICE_NO));
			DLFileVersion dlFileInvoice = getFileInfo(
					GetterUtil.getLong(doc.get(PaymentFileTerm.INVOICE_FILE_ENTRY_ID)));
			if (dlFileInvoice != null) {
				model.setInvoiceFileType(dlFileInvoice.getExtension());
				model.setInvoiceFileSize(dlFileInvoice.getSize());
			} else {
				model.setInvoiceFileType(StringPool.BLANK);
				model.setInvoiceFileSize(0L);
			}
			results.add(model);
		}

		return results;
	}

	/**
	 * Mapping Object PaymentFile with Object PaymentFileInputModel
	 * 
	 * @param paymentFile
	 * @return PaymentFileInputModel
	 */
	public static PaymentFileInputModel mappingToPaymentFileInputModel(PaymentFile paymentFile) {

		if (paymentFile == null) {
			return null;
		}

		PaymentFileInputModel model = new PaymentFileInputModel();

		model.setReferenceUid(paymentFile.getReferenceUid());
		model.setGovAgencyCode(paymentFile.getGovAgencyCode());
		model.setGovAgencyName(paymentFile.getGovAgencyName());
		// TODO:
		try {
			Dossier dossier = DossierLocalServiceUtil.getDossier(paymentFile.getDossierId());
			model.setApplicantName(dossier.getApplicantName());
			model.setApplicantIdNo(dossier.getApplicantIdNo());
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.setPaymentFee(paymentFile.getPaymentFee());
		model.setPaymentAmount(paymentFile.getPaymentAmount());
		model.setPaymentNote(paymentFile.getPaymentNote());
		model.setEpaymentProfile(paymentFile.getEpaymentProfile());

		return model;
	}

	/**
	 * Mapping Object PaymentFile with Object PaymentFileModel
	 * 
	 * @param paymentFile
	 * @return PaymentFileModel
	 */
	public static PaymentFileModel mappingToPaymentFileModel(PaymentFile paymentFile) {

		if (paymentFile == null) {
			return null;
		}

		PaymentFileModel model = new PaymentFileModel();

		model.setCreateDate(APIDateTimeUtils.convertDateToString(paymentFile.getCreateDate()));
		model.setModifiedDate(APIDateTimeUtils.convertDateToString(paymentFile.getModifiedDate()));
		model.setReferenceUid(paymentFile.getReferenceUid());
		model.setGovAgencyCode(paymentFile.getGovAgencyCode());
		model.setGovAgencyName(paymentFile.getGovAgencyName());
		// TODO:
		try {
			Dossier dossier = DossierLocalServiceUtil.getDossier(paymentFile.getDossierId());
			model.setApplicantName(dossier.getApplicantName());
			model.setApplicantIdNo(dossier.getApplicantIdNo());
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.setIsNew(GetterUtil.getInteger(paymentFile.getIsNew()));
		model.setPaymentFee(paymentFile.getPaymentFee());
		model.setPaymentAmount(paymentFile.getPaymentAmount());
		model.setPaymentNote(paymentFile.getPaymentNote());
		model.setBankInfo(paymentFile.getBankInfo());
		model.setEpaymentProfile(paymentFile.getEpaymentProfile());
		model.setPaymentStatus(paymentFile.getPaymentStatus());
		model.setPaymentMethod(paymentFile.getPaymentMethod());
		model.setConfirmDatetime(APIDateTimeUtils.convertDateToString(paymentFile.getConfirmDatetime()));
		model.setConfirmPayload(paymentFile.getConfirmPayload());
		DLFileVersion dlFilePayLoad = getFileInfo(GetterUtil.getLong(paymentFile.getConfirmFileEntryId()));
		if (dlFilePayLoad != null) {
			model.setConfirmFileType(dlFilePayLoad.getExtension());
			model.setConfirmFileSize(dlFilePayLoad.getSize());
		} else {
			model.setConfirmFileType(StringPool.BLANK);
			model.setConfirmFileSize(0L);
		}
		model.setConfirmNote(paymentFile.getConfirmNote());
		model.setApproveDatetime(APIDateTimeUtils.convertDateToString(paymentFile.getApproveDatetime()));
		model.setAccountUserName(paymentFile.getAccountUserName());
		model.setGovAgencyTaxNo(paymentFile.getGovAgencyTaxNo());
		model.setInvoiceTemplateNo(paymentFile.getInvoiceTemplateNo());
		model.setInvoiceIssueNo(paymentFile.getInvoiceIssueNo());
		model.setInvoiceNo(paymentFile.getInvoiceNo());
		DLFileVersion dlFileInvoice = getFileInfo(GetterUtil.getLong(paymentFile.getInvoiceFileEntryId()));
		if (dlFileInvoice != null) {
			model.setConfirmFileType(dlFileInvoice.getExtension());
			model.setConfirmFileSize(dlFileInvoice.getSize());
		} else {
			model.setConfirmFileType(StringPool.BLANK);
			model.setConfirmFileSize(0L);
		}

		return model;
	}

	/**
	 * Mapping document with Object PaymentFileSearchTemplateModel
	 * 
	 * @param documents
	 * @return List<PaymentFileSearchTemplateModel>
	 */
	public static List<PaymentFileSearchTemplateModel> mappingToPaymentFileSearchResultModel(List<Document> documents) {

		if (documents == null) {
			return null;
		}

		List<PaymentFileSearchTemplateModel> results = new ArrayList<PaymentFileSearchTemplateModel>();
		//
		for (Document doc : documents) {
			PaymentFileSearchTemplateModel model = new PaymentFileSearchTemplateModel();

			// model.setCreateDate(APIDateTimeUtils.convertDateToString(doc.getDate(PaymentFileTerm.CREATE_DATE)));
			// model.setModifiedDate(APIDateTimeUtils.convertDateToString(doc.getDate(PaymentFileTerm.MODIFIED_DATE)));
			model.setDossierId(GetterUtil.getLong(doc.get(PaymentFileTerm.DOSSIER_ID)));
			model.setDossierNo(doc.get(PaymentFileTerm.DOSSIER_NO));
			model.setCounter(GetterUtil.getInteger(PaymentFileTerm.COUNTER));
			model.setServiceCode(doc.get(PaymentFileTerm.SERVICE_CODE));
			model.setServiceName(doc.get(PaymentFileTerm.SERVICE_NAME));
			model.setReferenceUid(doc.get(PaymentFileTerm.REFERENCE_UID));
			model.setCreateDate(doc.get(PaymentFileTerm.CREATE_DATE));
			model.setModifiedDate(doc.get(PaymentFileTerm.MODIFIED_DATE));
			model.setGovAgencyCode(doc.get(PaymentFileTerm.GOV_AGENCY_CODE));
			model.setGovAgencyName(doc.get(PaymentFileTerm.GOV_AGENCY_NAME));
			model.setApplicantName(doc.get(PaymentFileTerm.APPLICANT_NAME));
			model.setApplicantIdNo(doc.get(PaymentFileTerm.APPLICANT_ID_NO));
			model.setIsNew(GetterUtil.getBoolean(doc.get(PaymentFileTerm.IS_NEW)));
			model.setPaymentFee(doc.get(PaymentFileTerm.PAYMENT_FEE));
			model.setPaymentAmount(GetterUtil.getLong(doc.get(PaymentFileTerm.PAYMENT_AMOUNT)));
			model.setPaymentNote(doc.get(PaymentFileTerm.PAYMENT_NOTE));
			model.setBankInfo(doc.get(PaymentFileTerm.BANK_INFO));
			model.setEpaymentProfile(doc.get(PaymentFileTerm.EPAYMENT_PROFILE));
			model.setPaymentStatus(Integer.parseInt(doc.get(PaymentFileTerm.PAYMENT_STATUS)));
			model.setPaymentMethod(doc.get(PaymentFileTerm.PAYMENT_METHOD));
			model.setConfirmDatetime(doc.get(PaymentFileTerm.CONFIRM_DATETIME));
			model.setConfirmPayload(doc.get(PaymentFileTerm.CONFIRM_PAYLOAD));
			DLFileVersion dlFilePayLoad = getFileInfo(
					GetterUtil.getLong(doc.get(PaymentFileTerm.CONFIRM_FILE_ENTRY_ID)));
			if (dlFilePayLoad != null) {
				model.setConfirmFileType(dlFilePayLoad.getExtension());
				model.setConfirmFileSize(dlFilePayLoad.getSize());
			} else {
				model.setConfirmFileType(StringPool.BLANK);
				model.setConfirmFileSize(0L);
			}
			model.setConfirmNote(doc.get(PaymentFileTerm.CONFIRM_NOTE));
			model.setApproveDatetime(doc.get(PaymentFileTerm.APPROVE_DATETIME));
			model.setAccountUserName(doc.get(PaymentFileTerm.ACCOUNT_USER_NAME));
			model.setGovAgencyTaxNo(doc.get(PaymentFileTerm.GOV_AGENCY_TAX_NO));
			model.setInvoiceTemplateNo(doc.get(PaymentFileTerm.INVOICE_TEMPLATE_NO));
			model.setInvoiceIssueNo(doc.get(PaymentFileTerm.INVOICE_ISSUE_NO));
			model.setInvoiceNo(doc.get(PaymentFileTerm.INVOICE_NO));
			DLFileVersion dlFileInvoice = getFileInfo(
					GetterUtil.getLong(doc.get(PaymentFileTerm.INVOICE_FILE_ENTRY_ID)));
			if (dlFileInvoice != null) {
				model.setInvoiceFileType(dlFileInvoice.getExtension());
				model.setInvoiceFileSize(dlFileInvoice.getSize());
			} else {
				model.setInvoiceFileType(StringPool.BLANK);
				model.setInvoiceFileSize(0L);
			}

			results.add(model);
		}

		return results;
	}

	/**
	 * Get info file upload
	 * 
	 * @param fileId
	 * @return DLFileVersion
	 */
	public static DLFileVersion getFileInfo(Long fileId) {
		DLFileVersion dlFileVersion = null;

		if (fileId > 0) {
			try {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileId);
				dlFileVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(fileEntry.getFileEntryId(), true);
			} catch (Exception e) {
				// TODO:
			}
		}
		return dlFileVersion;
	}

}
