package org.zerock.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="tbl_webreplies")
@EqualsAndHashCode(of="rno")
@ToString(exclude = "board")
public class WebReply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	private String replyText;
	private String replyer;
	
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updatedate;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private WebBoard board;
}
