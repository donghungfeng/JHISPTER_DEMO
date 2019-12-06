package com.api.hispterdemo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.hispterdemo.web.rest.TestUtil;

public class NhanSuTGTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NhanSuTG.class);
        NhanSuTG nhanSuTG1 = new NhanSuTG();
        nhanSuTG1.setId(1L);
        NhanSuTG nhanSuTG2 = new NhanSuTG();
        nhanSuTG2.setId(nhanSuTG1.getId());
        assertThat(nhanSuTG1).isEqualTo(nhanSuTG2);
        nhanSuTG2.setId(2L);
        assertThat(nhanSuTG1).isNotEqualTo(nhanSuTG2);
        nhanSuTG1.setId(null);
        assertThat(nhanSuTG1).isNotEqualTo(nhanSuTG2);
    }
}
