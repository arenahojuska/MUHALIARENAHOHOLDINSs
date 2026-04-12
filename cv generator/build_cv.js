/**
 * Muhali CV Builder
 * Matches exact style of Muhali_Dev_CV.pdf
 * Usage: node build_cv.js '<json>' /path/output.docx
 */

const {
  Document, Packer, Paragraph, TextRun,
  AlignmentType, BorderStyle, LevelFormat
} = require('docx');
const fs = require('fs');

const rawData = process.argv[2];
const outPath  = process.argv[3] || '/tmp/output_cv.docx';

let data;
try { data = JSON.parse(rawData); }
catch(e) { console.error('JSON parse error:', e.message); process.exit(1); }

// ── CONSTANTS ──────────────────────────────────────────────────
const FONT         = "Calibri";
const NAME_SIZE    = 38;
const ROLE_SIZE    = 21;
const CONTACT_SIZE = 18;
const SECTION_SIZE = 20;
const BODY_SIZE    = 19;
const BULLET_SIZE  = 19;
const KEYWORD_SIZE = 17;

const BLACK = "000000";
const DARK  = "1A1A1A";
const GRAY  = "555555";
const MID   = "333333";

// ── HELPERS ────────────────────────────────────────────────────
function rule() {
  return new Paragraph({
    spacing: { before: 0, after: 0 },
    border: { bottom: { style: BorderStyle.SINGLE, size: 6, color: BLACK, space: 1 } },
    children: []
  });
}

function gap(after = 80) {
  return new Paragraph({ spacing: { before: 0, after }, children: [] });
}

// ── NAME BLOCK ─────────────────────────────────────────────────
function buildNameBlock(name, title, contact) {
  const parts = [
    contact.phone,
    contact.email,
    contact.github  ? `github: ${contact.github}`   : null,
    contact.linkedin? `linkedin: ${contact.linkedin}`: null,
    contact.location
  ].filter(Boolean).join(' • ');

  return [
    new Paragraph({
      alignment: AlignmentType.CENTER,
      spacing: { before: 0, after: 30 },
      children: [new TextRun({ text: (name||'').toUpperCase(), bold: true, size: NAME_SIZE, font: FONT, color: BLACK })]
    }),
    new Paragraph({
      alignment: AlignmentType.CENTER,
      spacing: { before: 0, after: 50 },
      children: [new TextRun({ text: title||'', size: ROLE_SIZE, font: FONT, color: DARK })]
    }),
    new Paragraph({
      alignment: AlignmentType.CENTER,
      spacing: { before: 0, after: 100 },
      children: [new TextRun({ text: parts, size: CONTACT_SIZE, font: FONT, color: MID })]
    }),
    rule(),
  ];
}

// ── SECTION HEADER ─────────────────────────────────────────────
function buildSection(title) {
  return [
    gap(120),
    new Paragraph({
      spacing: { before: 0, after: 40 },
      children: [new TextRun({ text: title.toUpperCase(), bold: true, size: SECTION_SIZE, font: FONT, color: BLACK })]
    }),
    rule(),
    gap(60),
  ];
}

// ── SUMMARY ────────────────────────────────────────────────────
function buildSummary(text) {
  return [new Paragraph({
    spacing: { before: 0, after: 80 },
    children: [new TextRun({ text: text||'', size: BODY_SIZE, font: FONT, color: DARK })]
  })];
}

// ── SKILLS ─────────────────────────────────────────────────────
function buildSkills(skills) {
  return (skills||[]).map(g => new Paragraph({
    spacing: { before: 0, after: 40 },
    children: [
      new TextRun({ text: (g.label||'') + ': ', bold: true, size: BODY_SIZE, font: FONT, color: BLACK }),
      new TextRun({ text: g.items||'', size: BODY_SIZE, font: FONT, color: DARK })
    ]
  }));
}

// ── JOB ENTRY ──────────────────────────────────────────────────
function buildJob(exp) {
  const header = new Paragraph({
    spacing: { before: 0, after: 40 },
    children: [
      new TextRun({ text: (exp.title||'') + ' | ' + (exp.company||''), bold: true, size: BODY_SIZE, font: FONT, color: BLACK }),
      new TextRun({ text: ' — ' + (exp.location||''), size: BODY_SIZE, font: FONT, color: DARK }),
      new TextRun({ text: ' | ' + (exp.dates||''), size: BODY_SIZE, font: FONT, color: GRAY, italics: true }),
    ]
  });

  const desc = exp.description ? [new Paragraph({
    spacing: { before: 0, after: 30 },
    children: [new TextRun({ text: exp.description, size: BULLET_SIZE, font: FONT, color: DARK })]
  })] : [];

  const bullets = (exp.bullets||[]).map(b => new Paragraph({
    numbering: { reference: "cv-bullets", level: 0 },
    spacing: { before: 30, after: 30 },
    children: [new TextRun({ text: b, size: BULLET_SIZE, font: FONT, color: DARK })]
  }));

  return [gap(100), header, ...desc, ...bullets];
}

// ── PROJECT ENTRY ──────────────────────────────────────────────
function buildProject(proj) {
  return [
    gap(80),
    new Paragraph({
      spacing: { before: 0, after: 30 },
      children: [
        new TextRun({ text: (proj.name||''), bold: true, size: BODY_SIZE, font: FONT, color: BLACK }),
        proj.tech ? new TextRun({ text: ' — ' + proj.tech, size: BODY_SIZE, font: FONT, color: GRAY }) : new TextRun({text:''})
      ]
    }),
    new Paragraph({
      spacing: { before: 0, after: 40 },
      children: [new TextRun({ text: proj.description||'', size: BULLET_SIZE, font: FONT, color: DARK })]
    })
  ];
}

// ── EDUCATION ──────────────────────────────────────────────────
function buildEdu(edu) {
  return [
    gap(60),
    new Paragraph({
      spacing: { before: 0, after: 30 },
      children: [
        new TextRun({ text: (edu.degree||'') + ' — ' + (edu.institution||'') + ' (' + (edu.year||'') + ')', size: BODY_SIZE, font: FONT, color: DARK })
      ]
    }),
    ...(edu.detail ? [new Paragraph({
      spacing: { before: 0, after: 20 },
      children: [new TextRun({ text: edu.detail, size: BULLET_SIZE, font: FONT, color: GRAY, italics: true })]
    })] : [])
  ];
}

// ── CERTIFICATIONS ─────────────────────────────────────────────
function buildCert(cert) {
  return new Paragraph({
    numbering: { reference: "cv-bullets", level: 0 },
    spacing: { before: 30, after: 30 },
    children: [new TextRun({
      text: (cert.name||'') + (cert.issuer ? ' — ' + cert.issuer : '') + (cert.year ? ' (' + cert.year + ')' : ''),
      size: BULLET_SIZE, font: FONT, color: DARK
    })]
  });
}

// ── KEYWORDS ───────────────────────────────────────────────────
function buildKeywords(kw) {
  if (!kw || !kw.length) return [];
  return [new Paragraph({
    spacing: { before: 0, after: 0 },
    children: [new TextRun({ text: kw.join(' • '), size: KEYWORD_SIZE, font: FONT, color: GRAY })]
  })];
}

// ── ASSEMBLE ───────────────────────────────────────────────────
const contact  = data.contact || {};
const children = [...buildNameBlock(data.name, data.title, contact)];

if (data.summary) {
  children.push(...buildSection("Professional Summary"));
  children.push(...buildSummary(data.summary));
}
if (data.skills?.length) {
  children.push(...buildSection("Technical Skills"));
  children.push(...buildSkills(data.skills));
}
if (data.experience?.length) {
  children.push(...buildSection("Professional Experience"));
  data.experience.forEach(e => children.push(...buildJob(e)));
}
if (data.projects?.length) {
  children.push(...buildSection("Projects"));
  data.projects.forEach(p => children.push(...buildProject(p)));
}
if (data.education?.length) {
  children.push(...buildSection("Education"));
  data.education.forEach(e => children.push(...buildEdu(e)));
}
if (data.certifications?.length) {
  children.push(...buildSection("Certifications"));
  data.certifications.forEach(c => children.push(buildCert(c)));
}
if (data.keywords?.length) {
  children.push(...buildSection("Keywords & Technologies"));
  children.push(...buildKeywords(data.keywords));
}

// ── BUILD DOC ──────────────────────────────────────────────────
const doc = new Document({
  numbering: {
    config: [{
      reference: "cv-bullets",
      levels: [{
        level: 0,
        format: LevelFormat.BULLET,
        text: "\u2022",
        alignment: AlignmentType.LEFT,
        style: {
          paragraph: { indent: { left: 480, hanging: 260 } },
          run: { font: FONT, size: BULLET_SIZE, color: BLACK }
        }
      }]
    }]
  },
  styles: {
    default: { document: { run: { font: FONT, size: BODY_SIZE, color: DARK } } }
  },
  sections: [{
    properties: {
      page: {
        size: { width: 11906, height: 16838 },
        margin: { top: 900, right: 1000, bottom: 900, left: 1000 }
      }
    },
    children
  }]
});

Packer.toBuffer(doc).then(buf => {
  fs.writeFileSync(outPath, buf);
  console.log('OK:' + outPath);
}).catch(err => {
  console.error('BUILD_ERROR:' + err.message);
  process.exit(1);
});
