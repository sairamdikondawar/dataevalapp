package com.dataeval.util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dataeval.model.config.UserDetailsImpl;
import com.dataeval.model.entity.AuditEntity;
import com.dataeval.model.pojo.common.CommonCriteria;
import com.dataeval.model.pojo.common.SortInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Util {

	private static final Logger log = LoggerFactory.getLogger(Util.class);

	public static String pojoToJson(Object o) {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = null;
		try {
			// get Employee object as a json string
			jsonStr = mapperObj.writeValueAsString(o);
			System.out.println(jsonStr);
		} catch (IOException e) {
//			log.error("Util.pojoToJson > Exception :" + e.getMessage());
		}
		return jsonStr;
	}

	static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	public static ObjectMapper getObjectMapper() {
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	}

	public static Pageable getPageObjectFromCriteria(CommonCriteria criteria) {
		PageRequest page = PageRequest.of(0, 10);

		try {

			List<SortInfo> sortInfo = criteria.getSort();
			Sort sortFinal = null;

			if (criteria.getColumnName() != null && criteria.getOrder() != null) {
				String column = criteria.getColumnName().replaceAll("\"", "");
				sortFinal = Sort.by(column);
				if (criteria.getOrder() == 1) {
					sortFinal = sortFinal.descending();
				}
			}

			if (sortInfo != null) {

				for (SortInfo sort : sortInfo) {
					if (sort.getColumnName() != null) {
						if (sortFinal == null) {
							sortFinal = Sort.by(sort.getColumnName());
							if (sort.getOrder() == 0) {
								sortFinal = sortFinal.descending();
							}
						} else {
							Sort columSort = Sort.by(sort.getColumnName());

							if (sort.getOrder() == 1) {
								columSort = columSort.descending();
							}
							sortFinal = sortFinal.and(columSort);
						}
					}
				}
			}

			if (sortFinal == null) {
				page = PageRequest.of(criteria.getPage(), criteria.getSize());
			} else {
				page = PageRequest.of(criteria.getPage(), criteria.getSize(), sortFinal);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return page;

	}

	public static Sort getSortObjectFromCriteria(CommonCriteria criteria) {
		Sort sortFinal = Sort.by("creationDate"); // Note If any entitiy doesn't have creationDate column please add
													// that column
													// in particular table

		try {
			List<SortInfo> sortInfo = criteria.getSort();
			if (sortInfo != null) {
				for (SortInfo sort : sortInfo) {
					if (sort.getColumnName() != null) {
						if (sortFinal == null) {
							sortFinal = Sort.by(sort.getColumnName());
							if (sort.getOrder() == 0) {
								sortFinal = sortFinal.descending();
							}
						} else {
							Sort columSort = Sort.by(sort.getColumnName());
							if (sort.getOrder() == 0) {
								columSort = columSort.descending();
							}
							sortFinal = sortFinal.and(columSort);
						}
					}
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return sortFinal;

	}

	public static String getLoggedInUserType() {
		UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getAuthorities().iterator().next().getAuthority();
	}

	public static Integer getLoggedInUserId() {
		UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getId();
	}

	public static void updateHistory(AuditEntity entity, boolean isNew) {
		if (entity.getCreatedBy() == null|| isNew)
			entity.setCreatedBy(getLoggedInUserType());

		entity.setUpdatedBy(getLoggedInUserType());
		if (entity.getCreationDate() == null || isNew)
			entity.setCreationDate(new Date(System.currentTimeMillis()));
		entity.setUpdatedDate(new Date(System.currentTimeMillis()));
	}
	
	public static LocalDate  convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}

}
