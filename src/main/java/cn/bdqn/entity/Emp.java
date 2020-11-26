package cn.bdqn.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "emp")
public class Emp {
    private Integer empno;
    private String ename;
    private String address;
    private Integer deptno;

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", address='" + address + '\'' +
                ", deptno=" + deptno +
                '}';
    }

    public Emp(Integer empno, String ename, String address, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.address = address;
        this.deptno = deptno;
    }

    public Emp() {
    }
}
