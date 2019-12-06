package com.api.hispterdemo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.hispterdemo.web.rest.TestUtil;

public class XepLoaiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(XepLoai.class);
        XepLoai xepLoai1 = new XepLoai();
        xepLoai1.setId(1L);
        XepLoai xepLoai2 = new XepLoai();
        xepLoai2.setId(xepLoai1.getId());
        assertThat(xepLoai1).isEqualTo(xepLoai2);
        xepLoai2.setId(2L);
        assertThat(xepLoai1).isNotEqualTo(xepLoai2);
        xepLoai1.setId(null);
        assertThat(xepLoai1).isNotEqualTo(xepLoai2);
    }
}
