using System.ComponentModel.DataAnnotations;

namespace StudentGrades.Models.Common
{
    public class BaseEntity
    {
        [Key]
        public int Id { get; set; }
    }
}
