package br.com.rhribeiro25.SmartLog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@Entity
@Table(name = "logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{createdAt.not.null}")
    private Date createdAt;

    @NotBlank(message = "{ip.not.blank}")
    @Size(min = 9, max = 15, message = "{ip.size}")
    private String ip;

    @NotBlank(message = "{request.not.blank}")
    @Size(min = 12, max = 48, message = "{request.size}")
    private String request;

    @NotNull(message = "{status.not.null}")
    @Size(min = 100, max = 511, message = "{status.size}")
    private Integer status;

    @NotBlank(message = "{user.agent.not.blank}")
    @Size(min = 24, max = 256, message = "{user.agent.size}")
    private String userAgent;

}
