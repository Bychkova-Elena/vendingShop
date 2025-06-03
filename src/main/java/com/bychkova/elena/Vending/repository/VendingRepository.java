package com.bychkova.elena.Vending.repository;

import com.bychkova.elena.Vending.dto.VendingCreateUpdateRqDto;
import com.bychkova.elena.Vending.dto.VendingRsDto;
import com.bychkova.elena.Vending.enumeration.VendingStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class VendingRepository {
    private final JdbcTemplate jdbcTemplate;

    public VendingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class VendingRowMapper implements RowMapper<VendingRsDto> {
        public VendingRsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new VendingRsDto(
                    Long.valueOf(rs.getString("id")),
                    rs.getString("address"),
                    VendingStatus.valueOf(rs.getString("status")),
                    rs.getInt("capacity")
            );
        }
    }

    public List<VendingRsDto> findAll() {
        String sql = "SELECT * FROM vendings";
        return jdbcTemplate.query(sql, new VendingRowMapper());
    }

    public Optional<VendingRsDto> findById(Long id) {
        String sql = "SELECT * FROM vendings WHERE id = ?";
        List<VendingRsDto> results = jdbcTemplate.query(sql, new VendingRowMapper(), id);
        if (results.isEmpty()) return Optional.empty();
        return Optional.of(results.get(0));
    }

    public int save(VendingCreateUpdateRqDto vending) {
        String sql = "INSERT INTO vendings (address, status, capacity) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, vending.getAddress(), vending.getStatus().name(), vending.getCapacity());
    }

    public int update(Long id, VendingCreateUpdateRqDto vending) {
        String sql = "UPDATE vendings SET address = ?, status = ?, capacity = ? WHERE id = ?";
        return jdbcTemplate.update(sql, vending.getAddress(), vending.getStatus().name(), vending.getCapacity(), id);
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM vendings WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
