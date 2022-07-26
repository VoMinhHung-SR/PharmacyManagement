/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository.impl;

import com.vmh.pojo.Bill;
import com.vmh.pojo.ExaminationDetail;
import com.vmh.pojo.Medicine;
import com.vmh.pojo.MedicineUnit;
import com.vmh.pojo.Patient;
import com.vmh.pojo.Prescription;
import com.vmh.pojo.PrescriptionDetail;
import com.vmh.repository.AdminStatsReposioty;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class AdminStatsRepositoryImpl implements AdminStatsReposioty {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Object[]> getPatientStats() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

            Root<Patient> patientRoot = q.from(Patient.class);
            Root<ExaminationDetail> examinationDetailRoot = q.from(ExaminationDetail.class);

            //JOIN
            q.where(b.equal(examinationDetailRoot.get("patientId"), patientRoot.get("id")));

            q.multiselect(patientRoot.get("id"), patientRoot.get("firstName"),
                    patientRoot.get("lastName"), b.count(examinationDetailRoot.get("id")));

            q.groupBy(patientRoot.get("id"));

            Query query = session.createQuery(q);

            return query.getResultList();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object[]> getPatientDateStats(String kw, Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

            Root<Patient> patientRoot = q.from(Patient.class);
            Root<ExaminationDetail> examinationDetailRoot = q.from(ExaminationDetail.class);

            List<Predicate> pre = new ArrayList<>();
            if (kw != null && !kw.isEmpty()) {
                pre.add(b.like(patientRoot.get("lastName").as(String.class), String.format("%%%s%%", kw)));
            }

            if (fromDate != null) {
                pre.add(b.greaterThanOrEqualTo(examinationDetailRoot.get("createdDate").as(Date.class), fromDate));
            }

            if (toDate != null) {
                pre.add(b.lessThanOrEqualTo(examinationDetailRoot.get("createdDate").as(Date.class), toDate));
            }
            //JOIN
            pre.add(b.equal(examinationDetailRoot.get("patientId"), patientRoot.get("id")));
            q.where(pre.toArray(new Predicate[]{}));

            q.multiselect(b.function("MONTH", Integer.class, examinationDetailRoot.get("createdDate")),
                    b.function("YEAR", Integer.class, examinationDetailRoot.get("createdDate")),
                    b.count(examinationDetailRoot.get("id")));

            q.groupBy(b.function("MONTH", Integer.class, examinationDetailRoot.get("createdDate")),
                    b.function("YEAR", Integer.class, examinationDetailRoot.get("createdDate")));

            q = q.orderBy(b.asc(examinationDetailRoot.get("createdDate")));

            Query query = session.createQuery(q);

            return query.getResultList();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object[]> getRevenueByMonth(Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

            Root<Prescription> prescriptionRoot = q.from(Prescription.class);
            Root<Bill> billRoot = q.from(Bill.class);

            List<Predicate> pre = new ArrayList<>();
            if (fromDate != null) {
                pre.add(b.greaterThanOrEqualTo(billRoot.get("createdDate").as(Date.class), fromDate));
            }

            if (toDate != null) {
                pre.add(b.lessThanOrEqualTo(billRoot.get("createdDate").as(Date.class), toDate));
            }
            //JOIN
            pre.add(b.equal(billRoot.get("prescriptionBillId"), prescriptionRoot.get("id")));
            q.where(pre.toArray(new Predicate[]{}));

            q.multiselect(b.function("MONTH", Integer.class, billRoot.get("createdDate")),
                    b.function("YEAR", Integer.class, billRoot.get("createdDate")),
                    b.sum(billRoot.get("pay").as(Double.class)));

            q.groupBy(b.function("MONTH", Integer.class, billRoot.get("createdDate")),
                    b.function("YEAR", Integer.class, billRoot.get("createdDate")));

            q = q.orderBy(b.asc(billRoot.get("createdDate")));

            Query query = session.createQuery(q);

            return query.getResultList();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object[]> getMedicineFrequencyStats(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

            Root<Medicine> medicineRoot = q.from(Medicine.class);
            Root<MedicineUnit> medicineUniRoot = q.from(MedicineUnit.class);
            Root<PrescriptionDetail> prescriptionDetailRoot = q.from(PrescriptionDetail.class);

            List<Predicate> pre = new ArrayList<>();
            if (kw != null && !kw.isEmpty()) {
                pre.add(b.like(medicineRoot.get("name").as(String.class), String.format("%%%s%%", kw)));
            }

            //JOIN
            pre.add(b.equal(prescriptionDetailRoot.get("medicineUnitId"), medicineUniRoot.get("id")));
            pre.add(b.equal(medicineUniRoot.get("medicineId"), medicineRoot.get("id")));
            q.where(pre.toArray(new Predicate[]{}));

            q.multiselect(medicineRoot.get("name"),
                    b.sum(prescriptionDetailRoot.get("quantity").as(Integer.class)));

            q.groupBy(prescriptionDetailRoot.get("medicineUnitId"));

            q = q.orderBy();

            Query query = session.createQuery(q);

            return query.getResultList();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object[]> getRevenueByMonthOption(int month, int quarter, int year) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

            Root<ExaminationDetail> examinationDetailRoot = q.from(ExaminationDetail.class);
            Root<Prescription> prescriptionRoot = q.from(Prescription.class);
            Root<Bill> billRoot = q.from(Bill.class);

            List<Predicate> pre = new ArrayList<>();
            
            if (quarter > 0 && quarter <= 4) {
                Predicate p = b.equal(b.function("QUARTER", Integer.class, billRoot.get("createdDate")), quarter);
                pre.add(p);
            } else if (month > 0 && month <= 12) {
                Predicate p = b.equal(b.function("MONTH", Integer.class, billRoot.get("createdDate")), month);
                pre.add(p);
            }

            if (year > 1900 && year <= Year.now().getValue()) {
                Predicate p = b.equal(b.function("YEAR", Integer.class, billRoot.get("createdDate")), year);
                pre.add(p);
            }
            //JOIN
            pre.add(b.equal(billRoot.get("prescriptionBillId"), prescriptionRoot.get("id")));
            pre.add(b.equal(prescriptionRoot.get("examinationDetailId"), examinationDetailRoot.get("id")));
            
            q.where(pre.toArray(new Predicate[]{}));

            q.multiselect(b.function("MONTH", Integer.class, billRoot.get("createdDate")),
                    b.function("YEAR", Integer.class, billRoot.get("createdDate")),
                    b.sum(billRoot.get("pay").as(Double.class)),
                    b.sum(examinationDetailRoot.get("wage").as(Integer.class)));

            q.groupBy(b.function("MONTH", Integer.class, billRoot.get("createdDate")),
                    b.function("YEAR", Integer.class, billRoot.get("createdDate")));

            q = q.orderBy(b.asc(billRoot.get("createdDate")));

            Query query = session.createQuery(q);

            return query.getResultList();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return null;
    }

}
