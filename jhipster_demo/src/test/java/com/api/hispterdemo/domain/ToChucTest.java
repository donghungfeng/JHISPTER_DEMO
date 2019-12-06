package com.api.hispterdemo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.hispterdemo.web.rest.TestUtil;

public class ToChucTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ToChuc.class);
        ToChuc toChuc1 = new ToChuc();
        toChuc1.setId(1L);
        ToChuc toChuc2 = new ToChuc();
        toChuc2.setId(toChuc1.getId());
        assertThat(toChuc1).isEqualTo(toChuc2);
        toChuc2.setId(2L);
        assertThat(toChuc1).isNotEqualTo(toChuc2);
        toChuc1.setId(null);
        assertThat(toChuc1).isNotEqualTo(toChuc2);
    }
}
