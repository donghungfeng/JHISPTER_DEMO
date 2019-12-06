package com.api.hispterdemo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.hispterdemo.web.rest.TestUtil;

public class DeTaiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeTai.class);
        DeTai deTai1 = new DeTai();
        deTai1.setId(1L);
        DeTai deTai2 = new DeTai();
        deTai2.setId(deTai1.getId());
        assertThat(deTai1).isEqualTo(deTai2);
        deTai2.setId(2L);
        assertThat(deTai1).isNotEqualTo(deTai2);
        deTai1.setId(null);
        assertThat(deTai1).isNotEqualTo(deTai2);
    }
}
